package com.herbert.travelapp.api.core.trainRoute

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.core.route.RouteType
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.StationProvider
import org.springframework.stereotype.Component

@Component
class TrainRouteService(
    val trainRouteRepository: TrainRouteRepository,
    val stationProvider: StationProvider
) : TrainRouteProvider {
    override fun getAllRoutesFromStation(fromStation: Station): List<Route>? {
        val station = if (fromStation.apiId == null || fromStation.apiId == "null") {
            stationProvider.updateStationApiId(fromStation)
            return null
        } else fromStation
        if (station.apiId == "INVALID") return null
        val routes = trainRouteRepository.findRoutesFromStation(station) ?: return null

//        stationProvider.updateNonExistantApiIds(routes)
//        TODO("Make this call this asynchronous")

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
