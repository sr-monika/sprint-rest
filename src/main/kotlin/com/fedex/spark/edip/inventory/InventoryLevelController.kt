package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.InventoryLevelService.send
import com.fedex.spark.edip.inventory.InventoryLevelService.sendFailure
import com.fedex.spark.edip.inventory.model.InventoryLevelMessage
import com.fedex.spark.edip.inventory.model.InventoryLevelPayload
import com.fedex.spark.edip.inventory.model.SetOrAdjust
import io.swagger.v3.oas.annotations.Operation
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import arrow.core.flatMap

@RestController
class InventoryLevelController : BaseController {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/inventoryLevel/set")
    @Operation(description = "Set level for given sku and location_id to given quantity")
    fun postSetItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelPayload> {
        val result =
            InventoryLevelMessage.create(request, SetOrAdjust.SET).flatMap(
                { send(it).flatMap { Result.success(request) }}  )
        return super.returnResult(logger, result)
    }

    @PostMapping("/inventoryLevel/adjust")
    @Operation(description = "Adjust level for given sku and location_id with given quantity")
    fun postAdjustItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelPayload> {
        val result =
            InventoryLevelMessage.create(request, SetOrAdjust.ADJUST).flatMap(
                { send(it).flatMap { Result.success(request) }}
            )
        return super.returnResult(logger, result)
    }

    @PostMapping("/inventoryLevel/sendError")
    @Operation(description = "Trigger Error in send")
    fun postSendErrorItemLevel(@RequestBody request: InventoryLevelPayload): ResponseEntity<InventoryLevelPayload> {
        val result =
            InventoryLevelMessage.create(request, SetOrAdjust.SET).flatMap(
                { sendFailure(it).flatMap { Result.success(request) }}
            )
        return super.returnResult(logger, result)
    }
}

interface BaseController {

    fun <T> returnResult(logger: KLogger, result: Result<T>): ResponseEntity<T> {
        if (result.isFailure) {
            logger.error { "HttpStatus.BAD_REQUEST ${result.exceptionOrNull()?.message}  ${result.exceptionOrNull()}" }
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, result.exceptionOrNull()?.message, result.exceptionOrNull())
        }
        return ResponseEntity(result.getOrNull(), HttpStatus.OK)
    }
}

