package com.fedex.spark.edip.inventory.service

import com.fedex.spark.edip.inventory.model.InventoryLevel
import com.fedex.spark.edip.inventory.model.SetOrAdjust
import mu.KotlinLogging

object InventoryLevelMessageSender : DataOutlet<InventoryLevel> {

    private val logger = KotlinLogging.logger {}

    override fun inform(data: InventoryLevel): Result<InventoryLevel> {
        logger.info { data }
        return if (data.action == SetOrAdjust.FAILURE) {
            Result.failure(Exception("failed due to action == ${data.action}"))
        } else
            Result.success(data)
    }

}
