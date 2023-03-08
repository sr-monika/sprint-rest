package com.fedex.properties

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
open class ApiConfiguration(
    var clientId: String,
    var url: String,
    var key: String
)
@Configuration
@EnableConfigurationProperties(ApiConfiguration::class)
class AppConfig