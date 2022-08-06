package com.herbert.travelapp.api.core.airport

import org.springframework.stereotype.Component

@Component
class AirportService(
    val airportRepository: AirportRepository
): AirportProvider {
    override fun findAirportById(id: String): Airport? {
        return airportRepository.findAirportById(id)
    }

    override fun findAirportsByName(name: String): List<Airport>? {
        return airportRepository.findAirportsByName(name)
    }

    override fun findAirportByIATACode(iata: String): Airport? {
        return airportRepository.findAirportByIATACode(iata)
    }

    override fun findAirportByICAOCode(icao: String): Airport? {
        return airportRepository.findAirportByICAOCode(icao)
    }
}