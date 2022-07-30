package com.herbert.travelapp.api.core.trainRoute

interface TrainRouteProvider {

    fun getAllRoutesFromStation(stationId: String) : List<TrainRoute>?

}