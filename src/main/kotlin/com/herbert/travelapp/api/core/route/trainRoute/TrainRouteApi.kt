package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.core.station.Station

interface TrainRouteApi {

    fun findRoutesFromStation(fromStation: Station): List<TrainRoute>
}
