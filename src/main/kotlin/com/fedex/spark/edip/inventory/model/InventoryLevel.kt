package com.fedex.spark.edip.inventory.model

import io.swagger.v3.oas.annotations.media.Schema

data class InventoryLevelId(val id: Int)

data class InventoryLevel(
    val id: InventoryLevelId,
    val inventory_item: InventoryItem,
    val available: Quantity,
    val location_id: Int
)
