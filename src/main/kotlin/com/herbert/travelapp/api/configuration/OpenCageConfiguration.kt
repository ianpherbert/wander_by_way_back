package com.herbert.travelapp.api.configuration

import com.byteowls.jopencage.JOpenCageGeocoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JOpenCageConfiguration(
    @Value("\${open-cage.api-key}")
    val openCageApiKey: String
) {
    @Bean
    fun openCageConfiguration(): JOpenCageGeocoder {
        return JOpenCageGeocoder(openCageApiKey)
    }
}
