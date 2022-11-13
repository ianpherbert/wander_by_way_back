package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.route.Route

interface UpdateStationRoutes {
    fun updateStationRoutes(station: Station, routes: List<Route>): Station
}
