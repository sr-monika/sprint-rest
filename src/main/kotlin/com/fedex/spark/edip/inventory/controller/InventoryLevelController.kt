package com.fedex.spark.edip.inventory.controller

import arrow.core.flatMap
import com.fedex.spark.edip.inventory.model.InventoryLevel
import com.fedex.spark.edip.inventory.model.Quantity
import com.fedex.spark.edip.inventory.model.SetOrAdjust
import com.fedex.spark.edip.inventory.model.Sku
import com.fedex.spark.edip.inventory.model.UtcTimestamp
import com.fedex.spark.edip.inventory.nowTs
import com.fedex.spark.edip.inventory.service.InventoryLevelMessageSender
import com.fedex.spark.edip.inventory.service.SomeOtherService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class InventoryLevelController {
    private val someOtherService = SomeOtherService<InventoryLevel>()

    @PostMapping("/inventoryLevel/set")
    @Operation(description = "Set level for given sku and locatio to given quantity")
    fun postSetItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelResult> =
        processItemLevelChange(request, SetOrAdjust.SET)

    @PostMapping("/inventoryLevel/adjust")
    @Operation(description = "Adjust level for given sku and location with given quantity")
    fun postAdjustItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelResult> =
        processItemLevelChange(request, SetOrAdjust.ADJUST)

    @PostMapping("/inventoryLevel/failure")
    @Operation(description = "Trigger Error")
    fun postSendErrorItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelResult> =
        processItemLevelChange(request, SetOrAdjust.FAILURE)

    private fun processItemLevelChange(
        request: InventoryLevelPayload,
        action: SetOrAdjust
    ): ResponseEntity<InventoryLevelResult> {
        val data = InventoryLevelPayload.createModel(nowTs() /* should take from header */, request, action)
        data.onSuccess {
            InventoryLevelMessageSender.inform(it)
            someOtherService.inform(it)
        }
        return returnResult(data.flatMap { Result.success(InventoryLevelResult.create(it)) })
    }

    fun <T> returnResult(result: Result<T>): ResponseEntity<T> {
        if (result.isFailure) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                result.exceptionOrNull()?.message,
                result.exceptionOrNull()
            )
        }
        return ResponseEntity(result.getOrNull(), HttpStatus.OK)
    }
}


data class InventoryLevelPayload(
    @Schema(description = "utc timestamp")
    val inventoryEventTs: Long?,
    @Schema(description = "the item's sku - may not be an empty string", example = "UGS-BLK-10")
    val sku: String,
    @Schema(description = "quantity of items. negative number means removing from the inventory", example = "50")
    val quantity: Int,
    @Schema(description = "id of a location for the authenticated subOrg/Org", example = "validIdForSubOrg")
    val locationId: String
) {
    companion object {
        fun createModel(timeStamp: Long, level: InventoryLevelPayload, doAction: SetOrAdjust): Result<InventoryLevel> {
            return try {
                Result.success(
                    InventoryLevel(
                        sku = Sku.create(level.sku).getOrThrow(),
                        quantity = Quantity.create(level.quantity).getOrThrow(),
                        locationId = level.locationId,
                        action = doAction,
                        inventoryEventTs = UtcTimestamp.create(level.inventoryEventTs ?: timeStamp).getOrThrow()
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}


data class InventoryLevelResult(
    @Schema(description = "utc timestamp - denotes the triggering event's time stamp")
    val inventoryEventTs: Long?,
    val sku: String,
    val locationId: String,
    val action: SetOrAdjust
) {
    companion object {
        fun create(level: InventoryLevel) =
            InventoryLevelResult(
                inventoryEventTs = level.inventoryEventTs.value,
                sku = level.sku.value,
                locationId = level.locationId,
                action = level.action
            )
    }
}
