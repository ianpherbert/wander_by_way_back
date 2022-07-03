package com.herbert.travelapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TravelAppApplication

fun main(args: Array<String>) {
    runApplication<TravelAppApplication>(*args)
}
