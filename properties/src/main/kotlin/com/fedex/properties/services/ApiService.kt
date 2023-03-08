package com.fedex.properties.services

import com.fedex.properties.ApiConfiguration
import org.springframework.stereotype.Service

@Service
class ApiService(val apiConfiguration: ApiConfiguration) {
    init {
        println(apiConfiguration)
    }
}
