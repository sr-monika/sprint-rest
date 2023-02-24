package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryItem
import com.fedex.spark.edip.inventory.model.InventoryItemId
import com.fedex.spark.edip.inventory.model.InventoryItemRest
import com.fedex.spark.edip.inventory.model.InventoryLevel
import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.Money
import com.fedex.spark.edip.inventory.model.Quantity
import com.fedex.spark.edip.inventory.model.Sku
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

val item = InventoryItem(
    InventoryItemId(10), Money(120), true, Sku("UGS-blk-10")
)

val itemLevel = InventoryLevel(
    InventoryLevelId(99), item, Quantity(12), 78
)

@RestController
class InventoryController {
    @GetMapping("/itemlevel")
    fun home() = itemLevel.toRest()
}