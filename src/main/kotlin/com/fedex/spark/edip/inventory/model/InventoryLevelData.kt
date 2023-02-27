package com.fedex.spark.edip.inventory.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fedex.spark.edip.inventory.nowTs
import io.swagger.v3.oas.annotations.media.Schema

@JsonSerialize(using = InventoryIdSerializer::class)
data class InventoryLevelId(override val id: Int) : InventoryId

data class InventoryLevelPostBody(
    val effectiveTs : UtcTimestamp,
    val inventory_item: InventoryItem,
    val quantity: Quantity,
    val location_id: Int
)

data class InventoryLevelData(
    val id: InventoryLevelId,
    val inventory_item: InventoryItem,
    val available: Quantity,
    val location_id: Int,
    val effectiveTs: UtcTimestamp,
    val updatedTs: UtcTimestamp = UtcTimestamp(nowTs()),
)

data class InventoryItemId(override val id: Int) : InventoryId

@Schema
data class InventoryItem(
    val cost: Money,
    val requiresShipping: Boolean,
    val sku: Sku
)