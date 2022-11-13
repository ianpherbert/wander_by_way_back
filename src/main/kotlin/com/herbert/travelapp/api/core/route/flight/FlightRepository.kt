package com.herbert.travelapp.api.core.route.flight


interface FlightRepository {
    fun findFlights(from: String, to: String?, fromDate: String, toDate: String?) : List<Flight>?
}