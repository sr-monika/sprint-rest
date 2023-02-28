package com.fedex.spark.edip.inventory.model

data class InventoryLevelPayload(
    val effectiveTs : Long,
    val inventoryItem: InventoryItemPayload,
    val quantity: Int,
    val locationId: Int
)
enum class SetOrAdjust {
    SET, ADJUST
}

data class InventoryLevelMessage(
    val inventoryItem: InventoryItem,
    val available: Quantity,
    val locationId: Int,
    val action: SetOrAdjust,
    val effectiveTs: UtcTimestamp,
) {
    companion object {
        fun create(level : InventoryLevelPayload, doAction: SetOrAdjust): Result<InventoryLevelMessage> {
            return try {
                Result.success(
                    InventoryLevelMessage(
                        inventoryItem = InventoryItem.create(level.inventoryItem).getOrThrow(),
                        available = Quantity.create(level.quantity).getOrThrow(),
                        locationId = level.locationId,
                        action = doAction,
                        effectiveTs = UtcTimestamp.create(level.effectiveTs).getOrThrow())
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}


data class InventoryItemPayload(
    val cost: Double,
    val sku: String
)

data class InventoryItem(
    val cost: Money,
    val sku: Sku
) {
    companion object {
        fun create(item: InventoryItemPayload): Result<InventoryItem> {
            return try {
                Result.success(InventoryItem(Money.create(item.cost).getOrThrow(), Sku.create(item.sku).getOrThrow()))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}