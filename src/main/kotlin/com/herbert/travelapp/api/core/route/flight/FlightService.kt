package com.herbert.travelapp.api.core.route.flight

import com.herbert.travelapp.api.core.route.flight.useCase.FindAllFlightsFromAirportUseCase
import com.herbert.travelapp.api.core.route.flight.connector.FindEuropeanFlightsUseCase
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class FlightService(
    val findEuropeanFlightsUseCase: FindEuropeanFlightsUseCase
) : FindAllFlightsFromAirportUseCase {

    override fun findFlights(fromIataCode: String, fromDate: String, toDate: String): List<Flight> {
        return findEuropeanFlightsUseCase.findEuropeanFlights(fromIataCode, null, fromDate, toDate) ?: emptyList()
    }

    override fun findFlights(fromIataCode: String): List<Flight> {
        return findEuropeanFlightsUseCase.findEuropeanFlights(fromIataCode, null, today(), oneDayFrom()) ?: emptyList()
    }

    override fun findFlights(
        fromIataCode: String,
        toIataCode: String,
        fromDate: String,
        toDate: String
    ): List<Flight> {
        return findEuropeanFlightsUseCase.findEuropeanFlights(fromIataCode, toIataCode, fromDate, toDate) ?: emptyList()
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
