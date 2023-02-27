package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryLevelId
import com.fedex.spark.edip.inventory.model.InventoryLevelPostBody
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
class InventoryController {
    @GetMapping("/inventoryLevel/{levelId}")
    @Operation(description = "Retrieve item level for given id")
    fun getItemLevel(@PathVariable levelId: Int) =
        InventoryService.getItemLevel(InventoryLevelId(levelId)).fold(
            { ResponseEntity.ok(it) },
            { throw it }
        )

    @PostMapping("/inventoryLevel/set")
    @Operation(description = "Set level for given sku nd location_id by given Quantity")
    fun postItemLevel(@RequestBody request: InventoryLevelPostBody) =
        InventoryService.setItemLevel(request).fold(
            { ResponseEntity.ok(it) },
            { throw it }
        )

}