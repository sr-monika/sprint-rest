package com.fedex.spark.edip.inventory

import com.fedex.spark.edip.inventory.model.InventoryLevelMessage
import mu.KotlinLogging


object InventoryLevelService {

    private val logger = KotlinLogging.logger {}

    fun send(data: InventoryLevelMessage): Result<InventoryLevelMessage> {
        logger.info { "send $data"}
        return Result.success(data)
    }

    fun sendFailure(data: InventoryLevelMessage): Result<InventoryLevelMessage> {
        logger.error { "failed to send $data"}
        return Result.failure(Throwable("could not send"))
    }

}