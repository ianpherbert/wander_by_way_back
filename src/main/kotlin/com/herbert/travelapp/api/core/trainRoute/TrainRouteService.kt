package com.herbert.travelapp.api.core.trainRoute

import com.herbert.travelapp.api.core.station.FindStationApiId
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.StationProvider
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository,
    val stationProvider: StationProvider
) : TrainRouteProvider {

    override fun getAllRoutesFromStation(fromStation: Station): List<TrainRoute>? {

        val station = if (fromStation.apiId.isNullOrBlank()) {
            stationProvider.updateStationApiId(fromStation)
        } else fromStation

        if(station.apiId == "INVALID") return null

        return trainRouteRepository.findRoutesFromStation(station)
    }

}