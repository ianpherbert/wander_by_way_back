package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByIATACodeUseCase
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAreaIdUseCase
import com.herbert.travelapp.api.core.route.flight.FlightProvider
import com.herbert.travelapp.api.core.route.trainRoute.TrainRouteProvider
import com.herbert.travelapp.api.core.station.useCase.FindAllStationsByIdUseCase
import org.springframework.stereotype.Component

@Component
class RouteService(
    val trainRouteProvider: TrainRouteProvider,
    val flightProvider: FlightProvider,
    val findByCityIdUseCase: FindByCityIdUseCase,
    val findCitiesByAreaIdUseCase: FindCitiesByAreaIdUseCase,
    val findAllStationsByIdUseCase: FindAllStationsByIdUseCase,
    val findAirportsByIATACodeUseCase: FindAirportsByIATACodeUseCase
) : FindAllRoutesFromCityUseCase {
    override fun findAllRoutesFromCity(cityId: String): List<Route> {
        val city = findByCityIdUseCase.findCityById(cityId) ?: return emptyList()
        val connectedCities = findCitiesByAreaIdUseCase.findCitiesByAreaId(city.shareId!!)
        val stations = (city.getStationIds() + connectedCities.flatMap { it.getStationIds() }).distinct().let {
            findAllStationsByIdUseCase.findAllStationsByIdIn(it)
        }
        val trainRoutes = stations.flatMap { station ->
            station.routes.ifEmpty {
                trainRouteProvider.getAllRoutesFromStation(station)
            }
        }

        val flightRoutes = connectedCities.flatMap { it.getAirportIATACodes() }.distinct().map { airport ->
            flightProvider.findAllFlightsFromAirport(airport).let { flights ->
                flights.map {
                    it.to!!.airportCode!!
                }.let {
                    findAirportsByIATACodeUseCase.findAirportsByIATACode(it.plus(airport))
                }.let { destinationAirports ->
                    flights.mapNotNull { flight ->
                        val fromStop = destinationAirports.find { it.iata == flight.from!!.airportCode }
                        val toStop = destinationAirports.find { it.iata == flight.to!!.airportCode }
                        if (fromStop != null && toStop != null) {
                            flight.toRoute(toStop, fromStop)
                        } else {
                            null
                        }
                    }
                }
            }
        }.flatten()
        return listOf(trainRoutes, flightRoutes).flatten()
    }
}
