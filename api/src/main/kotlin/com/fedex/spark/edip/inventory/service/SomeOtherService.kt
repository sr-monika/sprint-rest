package com.fedex.spark.edip.inventory.service

import mu.KotlinLogging

class SomeOtherService<T>() : DataOutlet<T> {
    private val logger = KotlinLogging.logger {}

    override fun inform(data: T): T {
        logger.info { data }
        return data
    }
}
