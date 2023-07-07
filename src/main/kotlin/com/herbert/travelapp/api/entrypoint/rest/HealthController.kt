package com.herbert.travelapp.api.entrypoint.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping("/ping")
    fun ping(): Boolean{
        return true
    }
}