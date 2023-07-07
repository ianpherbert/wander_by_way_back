package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.core.route.RouteType
import com.herbert.travelapp.api.core.route.trainRoute.connector.TrainRouteFindFromStation
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesWithApiIdUseCase
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromStationUseCase
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.route.trainRoute.connector.TrainRouteFindStationInformation
import com.herbert.travelapp.api.core.station.useCase.UpdateStationApiIdUseCase
import com.herbert.travelapp.api.core.station.useCase.UpdateStationRoutesUseCase
import com.herbert.travelapp.api.utils.AsyncExecutor
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteFindFromStation: TrainRouteFindFromStation,
    val updateStationRoutesUseCase: UpdateStationRoutesUseCase,
    val updateStationApiIdUseCase: UpdateStationApiIdUseCase,
    val trainRouteFindStationInformation: TrainRouteFindStationInformation
) : GetAllRoutesFromStationUseCase, GetAllRoutesWithApiIdUseCase {
    private val asyncExecutor = AsyncExecutor()
    override fun getAllRoutesFromStation(fromStation: Station): List<Route> {
        // If the station has no apiId, update it before returning. Otherwise use existing station
        val station = if (fromStation.apiId == "INVALID") {
            return listOf()
        } else if (!fromStation.apiId.isNullOrBlank()) {
            updateStationApiIdUseCase.updateStationApiId(fromStation)
        } else fromStation


        val trainRoutes = findRoutesFromStation(station)
        if(fromStation.routes.isEmpty()){
            asyncExecutor.execute {
                updateStationRoutesUseCase.updateStationRoutes(station, trainRoutes)
            }
        }
        return trainRoutes
    }

    override fun getAllRoutesWithApiId(apiId: String): List<Route> {
        val station = trainRouteFindStationInformation.findStationInformation(apiId) ?: Station.dummyStation(apiId)
        val trainRoutes = findRoutesFromStation(station)
        // Add new station to database
        if (station.id !== "dummy") {
            asyncExecutor.execute {
                updateStationRoutesUseCase.updateStationRoutes(station, trainRoutes)
            }
        }
        return trainRoutes
    }
    
    private fun findRoutesFromStation(station: Station): List<Route> {
        val routes = trainRouteFindFromStation.findRoutes(station)
        return routes.map { route ->
            val to = RouteStop(
                name = route.toStationName ?: "",
                id = route.toStationId ?: "",
                latitude = route.latitude ?: 0.00,
                longitude = route.longitude ?: 0.00
            )
            val from = RouteStop(
                name = route.fromStationName ?: "",
                id = route.fromStationId ?: "",
                latitude = station.latitude,
                longitude = station.longitude
            )
            Route(
                to = to,
                type = RouteType.TRAIN,
                durationTotal = route.duration ?: 0,
                durationMinutes = route.durationMinutes ?: 0,
                durationHours = route.durationHours ?: 0
            ).apply {
                this.from = from
            }
        }
    }
}
