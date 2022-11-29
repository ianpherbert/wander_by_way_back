package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.core.route.RouteType
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.useCase.UpdateStationRoutesUseCase
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository,
    val updateStationRoutesUseCase: UpdateStationRoutesUseCase
) : TrainRouteProvider {
    override fun getAllRoutesFromStation(fromStation: Station): List<Route> {
        val station = if (fromStation.apiId == null || fromStation.apiId == "null" || fromStation.apiId == "INVALID") {
            return listOf()
        } else fromStation
        val routes = trainRouteRepository.findRoutesFromStation(station)
        val trainRoutes = routes.map { route ->
            val to = RouteStop().apply {
                name = route.toStationName
                id = route.toStationId
                latitude = route.latitude
                longitude = route.longitude
            }
            val from = RouteStop().apply {
                name = route.fromStationName
                id = route.fromStationId
                latitude = station.latitude
                longitude = station.longitude
            }
            Route().apply {
                this.to = to
                this.from = from
                this.type = RouteType.TRAIN
                this.durationTotal = route.duration
                this.durationMinutes = route.durationMinutes
                this.durationHours = route.durationHours
            }
        }
        updateStationRoutesUseCase.updateStationRoutes(station, trainRoutes)
        return trainRoutes
    }
}
