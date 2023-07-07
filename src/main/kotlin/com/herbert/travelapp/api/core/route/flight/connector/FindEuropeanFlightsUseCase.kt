package com.herbert.travelapp.api.core.route.flight.connector

import com.herbert.travelapp.api.core.route.flight.Flight

interface FindEuropeanFlightsUseCase {
    fun findEuropeanFlights(from: String, to: String?, fromDate: String, toDate: String?): List<Flight>?
}
