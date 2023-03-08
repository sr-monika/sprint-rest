package com.fedex.properties

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

// source: https://www.baeldung.com/kotlin/spring-boot-configurationproperties

@ConfigurationProperties(prefix = "api")
open class ApiConfiguration(
	var clientId: String,
	var url: String,
	var key: String
)

@Configuration
@EnableConfigurationProperties(ApiConfiguration::class)
class AppConfiguration


@Service
class ApiService(val apiConfiguration: ApiConfiguration) {
	init {
		println(apiConfiguration)
	}
}


@SpringBootApplication
class PropertiesApplication : CommandLineRunner {
	@Autowired
	var service: ApiService? = null
	override fun run(vararg args: String?) {
		println(service)
	}
}

fun main(args: Array<String>) {
	runApplication<PropertiesApplication>(*args)
}