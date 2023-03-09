package com.fedex.properties

import com.fedex.properties.services.ApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class Application : CommandLineRunner {

    @Autowired
    var service: ApiService? = null
    override fun run(vararg args: String?) {
        service?.logMe()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}