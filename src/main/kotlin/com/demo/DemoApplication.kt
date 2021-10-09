package com.demo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [DataSourceAutoConfiguration::class]            // enable empty db
)
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
