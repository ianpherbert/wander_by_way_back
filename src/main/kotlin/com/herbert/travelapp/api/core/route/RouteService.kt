package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.airport.AirportProvider
import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.flight.FlightProvider
import com.herbert.travelapp.api.core.station.StationProvider
import com.herbert.travelapp.api.core.trainRoute.TrainRouteProvider
import org.springframework.stereotype.Component

@Component
class RouteService(
    val trainRouteProvider: TrainRouteProvider,
    val flightProvider: FlightProvider,
    val cityProvider: CityProvider,
    val airportProvider: AirportProvider,
    val stationProvider: StationProvider
) : RouteProvider {
    override fun findAllRoutesFromCity(cityId: String): List<Route>? {
        val city = cityProvider.findCityById(cityId) ?: return null
        val connectedCities = cityProvider.findCitiesByAreaId(city.shareId!!)
        connectedCities
        val trainRoutes =
            stationProvider.findAllStationsByIdIn(connectedCities.flatMap { it.getStationIds() }.distinct())
                .map { station ->
                    trainRouteProvider.getAllRoutesFromStation(station) ?: listOf()
                }.flatten()

        val flightRoutes = connectedCities.flatMap { it.getAirportIATACodes() }.distinct().mapNotNull { airport ->
            val flights = flightProvider.findAllFlightsFromAirport(airport)

            val destinationAirports = flights?.map {
                it.to!!.airportCode!!
            }.let {
                airportProvider.findAirportsByIATACode(it!!.plus(airport))
            }

            flights?.mapNotNull { flight ->
                val fromStop = destinationAirports?.find { it.iata == flight.from!!.airportCode }
                val toStop = destinationAirports?.find { it.iata == flight.to!!.airportCode }
                if(fromStop != null && toStop != null){
                    flight.toRoute(toStop, fromStop)
                }else{
                    null
                }
            }
        }.flatten()
        return listOf(trainRoutes, flightRoutes).flatten()
    }
}