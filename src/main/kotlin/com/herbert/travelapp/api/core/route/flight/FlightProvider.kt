package com.herbert.travelapp.api.core.route.flight

interface FlightProvider {
    fun findAllFlightsFromAirport(iataCode: String, date: String, toDate: String): List<Flight>?

    fun findAllFlightsFromAirport(iataCode: String): List<Flight>?

    fun findFlightsBetweenAirports(fromAirportCode: String, toAirportCode: String, fromDate: String, toDate: String): List<Flight>?
}
