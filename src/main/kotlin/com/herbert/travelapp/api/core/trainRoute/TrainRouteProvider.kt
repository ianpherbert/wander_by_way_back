package com.herbert.travelapp.api.core.trainRoute

import com.herbert.travelapp.api.core.route.RailLocation

interface TrainRouteProvider {

    fun getAllRoutesFromStation(fromStation: RailLocation) : List<TrainRoute>?

}