package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.airport.useCase.*
import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.connector.CityGetAllByIataCodeOrAirportId
import com.herbert.travelapp.api.core.city.connector.CityGetAllByStationApiId
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAreaIdUseCase
import com.herbert.travelapp.api.core.route.flight.FlightProvider
import com.herbert.travelapp.api.core.route.trainRoute.useCase.GetAllRoutesFromStationUseCase
import com.herbert.travelapp.api.core.station.useCase.FindAllStationsByIdUseCase
import com.herbert.travelapp.api.utils.AsyncExecutor
import org.springframework.stereotype.Component

@Component
class RouteService(
    val getAllRoutesFromStationUseCase: GetAllRoutesFromStationUseCase,
    val flightProvider: FlightProvider,
    val findByCityIdUseCase: FindByCityIdUseCase,
    val findCitiesByAreaIdUseCase: FindCitiesByAreaIdUseCase,
    val findAllStationsByIdUseCase: FindAllStationsByIdUseCase,
    val findAirportsByIATACodeUseCase: FindAirportsByIATACodeUseCase,
    val findAllAirportsByIdInUseCase: FindAllAirportsByIdInUseCase,
    val updateAirportRoutesUseCase: UpdateAirportRoutesUseCase,
    val findCityGetAllByIataCodeOrAirportId: CityGetAllByIataCodeOrAirportId,
    val cityGetAllByStationApiId: CityGetAllByStationApiId
) : FindAllRoutesFromPoint {
    override fun findAllRoutes(routeSearchItem: RouteSearchItem): List<Route> {
        val connectedCities = when(routeSearchItem.type){
            PointType.CITY -> findByCityIdUseCase.findCityById(routeSearchItem.id)?.let {
                findCitiesByAreaIdUseCase.findCitiesByAreaId(it.shareId).plus(it)
            } ?: emptyList()
            PointType.AIRPORT -> findCityGetAllByIataCodeOrAirportId.getByAirportIdOrIata(routeSearchItem.id)

            PointType.STATION -> cityGetAllByStationApiId.findAllByStationApiId(routeSearchItem.id)
            else -> listOf()
        }

            val trainRoutes = if (routeSearchItem.filters.train) {
                getTrainRoutes(connectedCities)
            } else {
                emptyList()
            }
            val flightRoutes = if (routeSearchItem.filters.flight) {
                getFlightsFromConnectedCities(connectedCities)
            } else {
                emptyList()
            }
            return listOf(trainRoutes, flightRoutes).flatten()
    }
    
    private fun getTrainRoutes(connectedCities: List<City>): List<Route> {
        val stations = connectedCities.flatMap { it.getStationIds() }.distinct().let {
            findAllStationsByIdUseCase.findAllStationsByIdIn(it)
        }
        return stations.flatMap { station ->
            station.routes.ifEmpty {
                getAllRoutesFromStationUseCase.getAllRoutesFromStation(station)
            }
        }
    }

    private fun getFlightsFromConnectedCities(connectedCities: List<City>): List<Route> {
        val airports = connectedCities.flatMap { it.getAirportIds() }.let {
            findAllAirportsByIdInUseCase.findAllAirportsByIdIn(it)
        }
        return airports.flatMap { getFlightsFromAirport(it) }
    }

    private fun getFlightsFromAirport(airport: Airport): List<Route> {
        return if (airport.routes.isEmpty()) {
            searchFlights(airport.iata)
        } else {
            airport.routes
        }
    }

    private fun searchFlights(airportIATACode: String): List<Route> {
        val flights = flightProvider.findAllFlightsFromAirport(airportIATACode)
        val destinationAirports = flights.map {
            it.to!!.airportCode!!
        }.let {
            findAirportsByIATACodeUseCase.findAirportsByIATACode(it.plus(airportIATACode))
        }

        val routes = flights.mapNotNull { flight ->
            val fromStop = destinationAirports.find { it.iata == flight.from!!.airportCode }
            val toStop = destinationAirports.find { it.iata == flight.to!!.airportCode }
            if (fromStop != null && toStop != null) {
                flight.toRoute(toStop, fromStop)
            } else {
                null
            }
        }
        AsyncExecutor().let {
            it.execute { updateAirportRoutesUseCase.updateRoutes(airportIATACode, routes) }
        }

        return routes
    }
}
