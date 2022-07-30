package com.herbert.travelapp.api.core.flight

import com.herbert.travelapp.api.dataprovider.flight.FlightSearchResultData
import org.springframework.stereotype.Component

@Component
class FlightService(
    val flightRepository: FlightRepository
) : FlightProvider {

    override fun findAllRoutesFromAirport(airportCode: String, fromDate: String, toDate: String): List<Flight?> {
        return flightRepository.findFlights(airportCode, null, fromDate, toDate)
    }

    override fun findRoutesBetweenCities(
        fromAirportCode: String,
        toAirportCode: String,
        fromDate: String,
        toDate: String
    ): List<Flight?> {
        return flightRepository.findFlights(fromAirportCode, toAirportCode, fromDate, toDate)
    }
}


