package com.fedex.properties.services

import com.fedex.properties.ApiConfiguration
import org.springframework.stereotype.Service
import mu.KLogger
import mu.KLogging

@Service
class ApiService(val apiConfiguration: ApiConfiguration) {
    companion object : KLogging()

    fun logMe() {
        logger.info("apiConfig= $apiConfiguration")
        logger.debug("apiConfig= $apiConfiguration")

    }
}

