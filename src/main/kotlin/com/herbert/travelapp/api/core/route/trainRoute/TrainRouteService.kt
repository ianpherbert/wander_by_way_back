package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.core.route.RouteType
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromAPIIdUseCase
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromStationUseCase
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.connector.RouteFindStationInformation
import com.herbert.travelapp.api.core.station.useCase.UpdateStationApiIdUseCase
import com.herbert.travelapp.api.core.station.useCase.UpdateStationRoutesUseCase
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository,
    val updateStationRoutesUseCase: UpdateStationRoutesUseCase,
    val updateStationApiIdUseCase: UpdateStationApiIdUseCase,
    val routeFindStationInformation: RouteFindStationInformation
) : GetAllRoutesFromStationUseCase, GetAllRoutesFromAPIIdUseCase {
    override fun getAllRoutesFromStation(fromStation: Station): List<Route> {
        val station = if (fromStation.apiId == "INVALID") {
            return listOf()
        } else if (!fromStation.apiId.isNullOrBlank()) {
            updateStationApiIdUseCase.updateStationApiId(fromStation)
        } else fromStation
        val trainRoutes = mapRoutes(station)
        updateStationRoutesUseCase.updateStationRoutes(station, trainRoutes)
        return trainRoutes
    }

    override fun getAllRoutesFromAPIId(apiId: String): List<Route> {
        val station = routeFindStationInformation.findStationInformation(apiId) ?: Station().apply { this.apiId = apiId }
        val trainRoutes = mapRoutes(station)
        // Add new station to database
        if (!station.name.isNullOrBlank()) {
            updateStationRoutesUseCase.updateStationRoutes(station, trainRoutes)
        }
        return trainRoutes
    }
    
    private fun mapRoutes(station: Station): List<Route> {
        val routes = trainRouteRepository.findRoutesFromStation(station)
        return routes.map { route ->
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
    }
}
