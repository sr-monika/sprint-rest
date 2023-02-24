package com.fedex.spark.edip.inventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EdipInventoryApplication

fun main(args: Array<String>) {
	runApplication<EdipInventoryApplication>(*args)
}
