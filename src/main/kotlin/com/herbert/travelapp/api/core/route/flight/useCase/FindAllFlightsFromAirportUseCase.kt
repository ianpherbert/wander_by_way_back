package com.herbert.travelapp.api.core.route.flight.useCase

import com.herbert.travelapp.api.core.route.flight.Flight

interface FindAllFlightsFromAirportUseCase {
    fun findFlights(fromIataCode: String, date: String, toDate: String): List<Flight>

    fun findFlights(fromIataCode: String): List<Flight>

    fun findFlights(fromIataCode: String, toIataCode: String, fromDate: String, toDate: String): List<Flight>
}
