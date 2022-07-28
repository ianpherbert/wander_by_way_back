package com.herbert.travelapp.core.airport

import org.springframework.stereotype.Component

@Component
class AirportService(
    val airportRepository: AirportRepository
): AirportProvider {
}