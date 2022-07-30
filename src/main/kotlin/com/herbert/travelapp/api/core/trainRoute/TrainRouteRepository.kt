package com.herbert.travelapp.api.core.trainRoute

interface TrainRouteRepository {

    fun findRoutesFromStation(stationId: String) : List<TrainRoute>?

}