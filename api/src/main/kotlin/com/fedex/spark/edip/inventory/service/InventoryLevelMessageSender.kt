package com.fedex.spark.edip.inventory.service

import com.fedex.spark.edip.inventory.model.InventoryLevel
import com.fedex.spark.edip.inventory.model.SetOrAdjust
import mu.KotlinLogging

object InventoryLevelMessageSender : DataOutlet<InventoryLevel> {

    private val logger = KotlinLogging.logger {}

    override fun inform(data: InventoryLevel): InventoryLevel {
        logger.info { data }
        if (data.action == SetOrAdjust.FAILURE) {
            logger.error("failed due to action == ${data.action} -- how will we handle send errors ?")
        }
        return data
    }

}
