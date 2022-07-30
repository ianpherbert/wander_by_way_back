package com.herbert.travelapp.api.core.flight


interface FlightRepository {
    fun findFlights(from: String, to: String?, fromDate: String, toDate: String?) : List<Flight?>
}