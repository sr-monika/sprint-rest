package com.fedex.properties.services

import com.fedex.properties.ApiConfiguration
import org.springframework.stereotype.Service
import mu.KLogging

@Service
class ApiService(val apiConfiguration: ApiConfiguration) {
    companion object : KLogging()

    init {
        logger.info { apiConfiguration }
    }
}
