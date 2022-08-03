package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.flight.FlightProvider
import com.herbert.travelapp.api.core.trainRoute.TrainRouteProvider
import org.springframework.stereotype.Component

@Component
class RouteService(
    val trainRouteProvider: TrainRouteProvider,
    val flightProvider: FlightProvider,
    val cityProvider: CityProvider
) : RouteProvider {
    override fun findAllRoutesFromCity(cityId: String): List<Route>? {
        val city = cityProvider.findCityById(cityId) ?: return listOf()

        val trainRoutes = city.trainStations?.map{ station ->
            station.uicId?.let { trainRouteProvider.getAllRoutesFromStation(RailLocation(station.name!!, it)) }?.map { route ->
                val to = RouteCity().apply {
                    name = route.toStationName
                }
                val from = RouteCity().apply {
                    name = route.fromStationName
                }
                Route().apply {
                    this.to = to
                    this.from = from
                    this.type = RouteType.TRAIN
                    this.durationTotal = route.duration
                    this.durationMinutes = route.durationMinutes
                    this.durationHours = route.durationHours
                }
            } ?: listOf()
        }  ?: listOf()

        val flightRoutes = city.airports?.map { airport ->
                airport.iata?.let { flightProvider.findAllFlightsFromAirport(it) }?.map { flight ->
                    val to = RouteCity().apply {
                        name = flight?.to?.name
                    }
                    val from = RouteCity().apply {
                        name = flight?.from?.name
                    }
                    Route().apply {
                        this.to = to
                        this.from = from
                        this.type = RouteType.PLANE
                        //                Fix this !!!!
                        this.durationTotal = flight?.duration?.toInt()
                        this.durationMinutes = flight?.duration?.toInt()
                        this.durationHours = flight?.duration?.toInt()
                    }
                } ?: listOf()
            } ?: listOf()
        return listOf(trainRoutes, flightRoutes).flatten().flatten()
    }
}