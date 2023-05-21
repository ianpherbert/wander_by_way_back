package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByIATACodeUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAllAirportsByIdInUseCase
import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAreaIdUseCase
import com.herbert.travelapp.api.core.route.flight.Flight
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
    val findRoutesFromAPIIdUseCase: GetAllRoutesFromAPIIdUseCase,
    val findAllAirportsByIdInUseCase: FindAllAirportsByIdInUseCase
) : FindAllRoutesFromPoint {
    override fun findAllRoutes(routeSearchItem: RouteSearchItem): List<Route> {
        return if (routeSearchItem.type === PointType.CITY) {
            val city = findByCityIdUseCase.findCityById(routeSearchItem.id) ?: return emptyList()
            val connectedCities = findCitiesByAreaIdUseCase.findCitiesByAreaId(city.shareId)
            val trainRoutes = if (routeSearchItem.filters.train) {
                getTrainRoutes(city, connectedCities)
            } else {
                emptyList()
            }
            val flightRoutes = if (routeSearchItem.filters.flight) {
                getFlightRoutes(connectedCities)
            } else {
                emptyList()
            }
            listOf(trainRoutes, flightRoutes).flatten()
        } else if (routeSearchItem.type == PointType.STATION) {
            findStationsByApiIdUseCase.findStationByApiId(routeSearchItem.id)?.let { station ->
                getAllRoutesFromStationUseCase.getAllRoutesFromStation(station)
            } ?: findRoutesFromAPIIdUseCase.getAllRoutesFromAPIId(routeSearchItem.id)
        } else if (routeSearchItem.type == PointType.AIRPORT) {
            mapFlightsToAirports(flightProvider.findAllFlightsFromAirport(routeSearchItem.id), routeSearchItem.id)
        } else {
            listOf()
        }
    }
    
    private fun getTrainRoutes(city: City, connectedCities: List<City>): List<Route> {
        val stations = (city.getStationIds() + connectedCities.flatMap { it.getStationIds() }).distinct().let {
            findAllStationsByIdUseCase.findAllStationsByIdIn(it)
        }
        return stations.flatMap { station ->
            station.routes.ifEmpty {
                getAllRoutesFromStationUseCase.getAllRoutesFromStation(station)
            }
        }
    }

    private fun getFlightRoutes(connectedCities: List<City>): List<Route> {
        val airports = connectedCities.flatMap { it.getAirportIds() }.let{
            findAllAirportsByIdInUseCase.findAllAirportsByIdIn(it)
        }
        val searchedRoutes = airports.filter{ it.routes.isEmpty() }.map{
            it.iata
        }.distinct().map { airport ->
            mapFlightsToAirports(flightProvider.findAllFlightsFromAirport(airport), airport)
        }.flatten()
        val existingRoutes = airports.filter{it.routes.isNotEmpty()}.flatMap{it.routes}
        return listOf(searchedRoutes, existingRoutes).flatten()
    }

    private fun mapFlightsToAirports(flights: List<Flight>, airport: String): List<Route> {
        return flights.map {
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
}
