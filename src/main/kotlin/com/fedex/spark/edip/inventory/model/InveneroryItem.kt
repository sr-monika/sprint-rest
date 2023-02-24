package com.fedex.spark.edip.inventory.model

import io.swagger.v3.oas.annotations.media.Schema

data class InventoryItemId(val id: Int);


@Schema
data class InventoryItem(
    val id: InventoryItemId,
    val cost: Money,
    val requires_shipping: Boolean,
    val sku: Sku
)  {
    fun toRest() : InventoryItemRest = InventoryItemRest(id.id, cost = cost.value, requires_shipping, sku.value)
}

data class InventoryItemRest(
    val id: Int,
    val cost: Int,
    val requires_shipping: Boolean,
    val sku: String,
)  {
}