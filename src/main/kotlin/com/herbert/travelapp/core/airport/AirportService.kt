package com.herbert.travelapp.core.airport

import org.springframework.stereotype.Component

@Component
class AirportService(
    val airportRepository: AirportRepository
): AirportProvider {
    override fun findAirportById(id: String): Airport? {
        TODO("Not yet implemented")
    }

    override fun findAirportByName(name: String): Airport? {
        TODO("Not yet implemented")
    }

    override fun findAirportByIATACode(iata: String): Airport? {
        TODO("Not yet implemented")
    }

    override fun findAirportByICAOCode(icao: String): Airport? {
        TODO("Not yet implemented")
    }
}