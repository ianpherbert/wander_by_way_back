package com.herbert.travelapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class TravelAppApplication

fun main(args: Array<String>) {
    runApplication<TravelAppApplication>(*args)
}
