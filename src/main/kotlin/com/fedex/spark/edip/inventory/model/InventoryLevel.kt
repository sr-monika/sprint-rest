package com.fedex.spark.edip.inventory.model

data class InventoryLevel(
    val inventoryEventTs: UtcTimestamp,
    val action: SetOrAdjust,
    val sku: Sku,
    val quantity: Quantity,
    val locationId: String,
    val org: Organization,
    val subOrg: SubOrganization
)
