package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.route.Route

interface UpdateAirportRoutesUseCase {
    fun updateRoutes(airport: Airport, routes: List<Route>): Airport
}
