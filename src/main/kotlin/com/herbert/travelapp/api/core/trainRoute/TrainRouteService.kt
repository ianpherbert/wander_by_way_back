package com.herbert.travelapp.api.core.trainRoute

import com.herbert.travelapp.api.core.route.RailLocation
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository
) : TrainRouteProvider {

    override fun getAllRoutesFromStation(fromStation: RailLocation): List<TrainRoute>? {
        return trainRouteRepository.findRoutesFromStation(fromStation)
    }

}