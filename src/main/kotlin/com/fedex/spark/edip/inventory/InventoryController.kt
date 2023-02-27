package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.InventoryLevelPostBody
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class InventoryController {
    @GetMapping("/inventoryLevel/{levelId}")
    @Operation(description = "Retrieve item level for given id")
    fun getItemLevel(@PathVariable levelId: Int) = getItemLevel(InventoryLevelId(levelId))


    @PostMapping("/inventoryLevel/set")
    @Operation(description = "Adjust level of given sku by given Quantity")
    fun postItemLevel(@RequestBody request: InventoryLevelPostBody) =
        InventoryService.setItemLevel(request)

}