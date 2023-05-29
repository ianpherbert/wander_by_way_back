package com.herbert.travelapp.api.configuration

import io.github.oshai.KLogger
import io.github.oshai.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoggerConfiguration {

    @Bean
    fun logger(): KLogger {
        return KotlinLogging.logger {}
    }
}