package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryItem
import com.fedex.spark.edip.inventory.model.InventoryItemId
import com.fedex.spark.edip.inventory.model.InventoryLevel
import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.Money
import com.fedex.spark.edip.inventory.model.Quantity
import com.fedex.spark.edip.inventory.model.Sku
import com.fedex.spark.edip.inventory.model.UtcTimestamp
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


val item = InventoryItem(
    InventoryItemId(10), Money(120), true, Sku("UGS-blk-10")
)

val itemLevel = InventoryLevel(
    InventoryLevelId(99), item, Quantity(12), 78, effectiveTs = UtcTimestamp(0))

@RestController
class InventoryController {
    @GetMapping("/itemlevel/{levelId}")
    @Operation(description = "Retrieve item level for given id")
    @ResponseBody
    fun getItemLevel(@PathVariable levelId: Int): InventoryLevel =
        itemLevel.copy(id = InventoryLevelId(levelId),
            effectiveTs = UtcTimestamp(nowTs()))
}