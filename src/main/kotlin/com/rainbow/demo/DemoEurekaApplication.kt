package com.rainbow.demo

import de.codecentric.boot.admin.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@EnableAdminServer
@EnableAutoConfiguration
@Configuration
class DemoEurekaApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoEurekaApplication::class.java, *args)
}
