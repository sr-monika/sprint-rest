package com.fedex.spark.edip.inventory.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema

@JsonSerialize(using = InventoryIdSerializer::class)
data class InventoryLevelId(override val id: Int) : InventoryId

data class InventoryLevel(
    val id: InventoryLevelId,
    val inventory_item: InventoryItem,
    val available: Quantity,
    val location_id: Int,
    val effectiveTs : UtcTimestamp,
    val version : String = "23"
)

data class InventoryItemId(override val id: Int) : InventoryId

@Schema
data class InventoryItem(
    val id: InventoryItemId,
    val cost: Money,
    val requiresShipping: Boolean,
    val sku: Sku
)