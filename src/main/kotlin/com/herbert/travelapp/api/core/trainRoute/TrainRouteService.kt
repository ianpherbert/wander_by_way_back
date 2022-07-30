package com.herbert.travelapp.api.core.trainRoute

import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository
) : TrainRouteProvider {

    override fun getAllRoutesFromStation(stationId: String): List<TrainRoute>? {
        return trainRouteRepository.findRoutesFromStation(stationId)
    }

}