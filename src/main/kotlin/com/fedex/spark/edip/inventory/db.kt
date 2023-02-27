package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryItem
import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.InventoryLevelData
import com.fedex.spark.edip.inventory.model.Money
import com.fedex.spark.edip.inventory.model.Quantity
import com.fedex.spark.edip.inventory.model.Sku
import com.fedex.spark.edip.inventory.model.UtcTimestamp

val itemLevels = HashMap<InventoryLevelId, InventoryLevelData>()
var itemLevelsId = 0;

fun seedData() {
    val itemLevelId = InventoryLevelId(0)
    itemLevels[itemLevelId] =
        InventoryLevelData(
            itemLevelId,
            InventoryItem(Money(120), true, Sku("UGS-blk-10")),
            Quantity(12),
            0,
            UtcTimestamp(0),
            UtcTimestamp(10)
        )
}


fun findLevelBySkuLocation(sku: Sku, locationId: Int): InventoryLevelData? =
    itemLevels.filter { it.value.location_id == locationId && it.value.inventory_item.sku == sku }
        .firstNotNullOfOrNull { it.value }


fun insertItemLevel(data: InventoryLevelData): InventoryLevelData {
    itemLevelsId += 1
    itemLevels[InventoryLevelId(itemLevelsId)] = data
    return data.copy(id = InventoryLevelId(itemLevelsId))
}

fun updateItemLevel(id: InventoryLevelId, data: InventoryLevelData): InventoryLevelData {
    val level = data.copy(updatedTs = UtcTimestamp(nowTs()))
    itemLevels[id] = level
    return level
}

