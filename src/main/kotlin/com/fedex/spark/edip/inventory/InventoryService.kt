package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryLevelData
import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.InventoryLevelPostBody
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException


object InventoryService {

    fun getItemLevel(id: InventoryLevelId): Result<InventoryLevelData> {
        val level = itemLevels[id]
        return if (level != null)
            Result.success(level)
        else
            Result.failure(
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No InvenoryItemLevel found for id $id"
                )
            )
    }

    fun setItemLevel(request: InventoryLevelPostBody): Result<InventoryLevelData> {
        val level = findLevelBySkuLocation(request.inventory_item.sku, request.location_id)
        return if (level == null) {
            Result.success(
                insertItemLevel(
                    InventoryLevelData(
                        InventoryLevelId(0),
                        request.inventory_item,
                        request.quantity,
                        request.location_id,
                        request.effectiveTs
                    )
                )
            )
        } else {
            Result.success(
                updateItemLevel(
                    level.id, InventoryLevelData(
                        level.id,
                        level.inventory_item, request.quantity, request.location_id, request.effectiveTs
                    )
                )
            )
        }
    }

}