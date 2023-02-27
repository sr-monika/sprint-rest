package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryLevelData
import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.InventoryLevelPostBody


object InventoryService {
    fun setItemLevel(request: InventoryLevelPostBody): InventoryLevelData {
        val level = findLevelBySkuLocation(request.inventory_item.sku, request.location_id)
        return if (level == null) {
            insertItemLevel(
                InventoryLevelData(
                    InventoryLevelId(0),
                    request.inventory_item,
                    request.quantity,
                    request.location_id,
                    request.effectiveTs
                )
            )
        } else {
            updateItemLevel(
                level.id, InventoryLevelData(
                    level.id,
                    level.inventory_item, request.quantity, request.location_id, request.effectiveTs
                )
            )
        }
    }

}