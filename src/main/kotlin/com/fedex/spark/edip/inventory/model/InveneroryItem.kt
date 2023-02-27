package com.fedex.spark.edip.inventory.model

import io.swagger.v3.oas.annotations.media.Schema

data class InventoryItemId(val id: Int);


@Schema
data class InventoryItem(
    val id: InventoryItemId,
    val cost: Money,
    val requires_shipping: Boolean,
    val sku: Sku
)