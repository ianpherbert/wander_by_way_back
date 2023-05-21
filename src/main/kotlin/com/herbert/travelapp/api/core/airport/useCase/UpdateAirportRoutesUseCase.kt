package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.route.Route

interface UpdateAirportRoutesUseCase {
    fun updateRoutes(airportIATACode: String, routes: List<Route>): Airport
}
