package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.station.Station

interface UpdateStationRoutesUseCase {
    fun updateStationRoutes(station: Station, routes: List<Route>): Station
}
