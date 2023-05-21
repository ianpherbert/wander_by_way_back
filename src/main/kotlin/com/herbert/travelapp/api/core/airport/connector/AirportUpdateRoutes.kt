package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.route.Route

interface AirportUpdateRoutes {
    fun updateRoutes(airportIATACode: String, routes: List<Route>): Airport
}
