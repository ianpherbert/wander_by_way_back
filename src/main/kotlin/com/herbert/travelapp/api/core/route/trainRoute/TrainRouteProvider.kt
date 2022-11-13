package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.station.Station

interface TrainRouteProvider {

    fun getAllRoutesFromStation(fromStation: Station): List<Route>
}
