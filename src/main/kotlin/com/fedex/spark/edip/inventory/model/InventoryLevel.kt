package com.fedex.spark.edip.inventory.model

data class InventoryLevel(
    val inventoryItem: InventoryItem,
    val available: Quantity,
    val locationId: Int,
    val action: SetOrAdjust,
    val effectiveTs: UtcTimestamp,
)

data class InventoryItem(
    val cost: Money,
    val sku: Sku
)