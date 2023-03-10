package com.herbert.travelapp.api.core.route.flight

interface FlightRepository {
    fun findEuropeanFlights(from: String, to: String?, fromDate: String, toDate: String?): List<Flight>?
}
