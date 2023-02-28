package com.fedex.spark.edip.inventory.model

data class InventoryLevelId(val id: Int)

data class InventoryLevelPayload(
    val effectiveTs : Long,
    val inventory_item: InventoryItemPayload,
    val quantity: Int,
    val location_id: Int
)
enum class SetOrAdjust {
    SET, ADJUST
}

data class InventoryLevelMessage(
    val inventory_item: InventoryItem,
    val available: Quantity,
    val location_id: Int,
    val action: SetOrAdjust,
    val effectiveTs: UtcTimestamp,
) {
    companion object {
        fun create(level : InventoryLevelPayload, doAction: SetOrAdjust): Result<InventoryLevelMessage> {
            return try {
                Result.success(
                    InventoryLevelMessage(
                        inventory_item = InventoryItem.create(level.inventory_item).getOrThrow(),
                        available = Quantity.create(level.quantity).getOrThrow(),
                        location_id = level.location_id,
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