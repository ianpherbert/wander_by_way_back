package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByIATACodeUseCase
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAreaIdUseCase
import com.herbert.travelapp.api.core.route.flight.FlightProvider
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromAPIIdUseCase
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromStationUseCase
import com.herbert.travelapp.api.core.station.useCase.FindAllStationsByIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindStationsByApiIdUseCase
import org.springframework.stereotype.Component

@Component
class RouteService(
    val getAllRoutesFromStationUseCase: GetAllRoutesFromStationUseCase,
    val flightProvider: FlightProvider,
    val findByCityIdUseCase: FindByCityIdUseCase,
    val findCitiesByAreaIdUseCase: FindCitiesByAreaIdUseCase,
    val findAllStationsByIdUseCase: FindAllStationsByIdUseCase,
    val findStationsByApiIdUseCase: FindStationsByApiIdUseCase,
    val findAirportsByIATACodeUseCase: FindAirportsByIATACodeUseCase,
    val findRoutesFromAPIIdUseCase: GetAllRoutesFromAPIIdUseCase
) : FindAllRoutesFromPoint {
    override fun findAllRoutes(routeSearchItem: RouteSearchItem): List<Route> {
        return if (routeSearchItem.type === PointType.CITY) {
            val city = findByCityIdUseCase.findCityById(routeSearchItem.id) ?: return emptyList()
            val connectedCities = findCitiesByAreaIdUseCase.findCitiesByAreaId(city.shareId)
            val stations = (city.getStationIds() + connectedCities.flatMap { it.getStationIds() }).distinct().let {
                findAllStationsByIdUseCase.findAllStationsByIdIn(it)
            }
            val trainRoutes = stations.flatMap { station ->
                station.routes.ifEmpty {
                    getAllRoutesFromStationUseCase.getAllRoutesFromStation(station)
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
            listOf(trainRoutes, flightRoutes).flatten()
        } else if (routeSearchItem.type == PointType.STATION) {
            findStationsByApiIdUseCase.findStationByApiId(routeSearchItem.id)?.let { station ->
                getAllRoutesFromStationUseCase.getAllRoutesFromStation(station)
            } ?: findRoutesFromAPIIdUseCase.getAllRoutesFromAPIId(routeSearchItem.id)
        } else if (routeSearchItem.type == PointType.AIRPORT) {
            flightProvider.findAllFlightsFromAirport(routeSearchItem.id).let { flights ->
                flights.map {
                    it.to!!.airportCode!!
                }.let {
                    findAirportsByIATACodeUseCase.findAirportsByIATACode(it.plus(routeSearchItem.id))
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
        } else {
            listOf()
        }
    }
}
