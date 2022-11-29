package com.herbert.travelapp.api.core.route.flight

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class FlightService(
    val flightRepository: FlightRepository
) : FlightProvider {

    override fun findAllFlightsFromAirport(iataCode: String, fromDate: String, toDate: String): List<Flight> {
        return flightRepository.findFlights(iataCode, null, fromDate, toDate) ?: emptyList()
    }

    override fun findAllFlightsFromAirport(iataCode: String): List<Flight> {
        return flightRepository.findFlights(iataCode, null, today(), oneDayFrom()) ?: emptyList()
    }

    override fun findFlightsBetweenAirports(
        fromAirportCode: String,
        toAirportCode: String,
        fromDate: String,
        toDate: String
    ): List<Flight> {
        return flightRepository.findFlights(fromAirportCode, toAirportCode, fromDate, toDate) ?: emptyList()
    }

    private fun today(): String {
        return formatDate(LocalDate.now())
    }
    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
    }

    private fun oneDayFrom(): String {
        return formatDate(
            today().split("/").map { it.toInt() }.let {
                LocalDate.of(it[2], it[1], it[0]).plusDays(1)
            }
        )
    }
}
