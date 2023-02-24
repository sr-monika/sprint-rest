package com.fedex.spark.edip.inventory.model

import io.swagger.v3.oas.annotations.media.Schema

data class InventoryLevelId(val id: Int)

data class InventoryLevel(
    val id: InventoryLevelId,
    val inventory_item: InventoryItem,
    val available: Quantity,
    val location_id: Int
) {
    fun toRest() = InventoryLevelRest(
        id.id,
        inventory_item.toRest(),
        available.value,
        location_id
    )
}

data class InventoryLevelRest(
    val id: Int,
    val inventory_item: InventoryItemRest,
    val available: Int,
    val location_id: Int,
    val version: String = "1",
)