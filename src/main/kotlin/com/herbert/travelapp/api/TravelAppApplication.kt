package com.herbert.travelapp.api

import io.mongock.runner.springboot.EnableMongock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableMongock
class TravelAppApplication

fun main(args: Array<String>) {
    runApplication<TravelAppApplication>(*args)
}
