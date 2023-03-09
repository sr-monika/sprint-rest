package com.fedex.spark.edip.inventory

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfiguration {
	@Bean
	fun springShopOpenAPI(): OpenAPI? = OpenAPI()
		.info(
			io.swagger.v3.oas.models.info.Info().title("Spark Inventory API")
				.description("Spark Inventory Api to track an orgranzations inventory")
				.version("v0.0.1")
		)
		.externalDocs(
			ExternalDocumentation()
				.description("Open Api Doc")
				.url("http://springdoc.org")
		)
}

@SpringBootApplication
open class EdipInventoryApplication

fun main(args: Array<String>) {
	runApplication<EdipInventoryApplication>(*args)
}
