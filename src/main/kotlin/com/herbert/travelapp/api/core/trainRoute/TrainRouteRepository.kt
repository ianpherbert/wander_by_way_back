package com.herbert.travelapp.api.core.trainRoute

import com.herbert.travelapp.api.core.route.RailLocation

interface TrainRouteRepository {

    fun findRoutesFromStation(fromStation: RailLocation) : List<TrainRoute>?

}