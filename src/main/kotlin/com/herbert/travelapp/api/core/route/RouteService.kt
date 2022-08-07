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
        val trainRoutes = city.trainStations?.map{ station ->
            trainRouteProvider.getAllRoutesFromStation(station)?.map { route ->
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
            } ?: listOf()
        }  ?: listOf()

        val flightRoutes = city.airports?.map { airport ->
                val flights = airport.iata?.let { flightProvider.findAllFlightsFromAirport(it) }

                val airports = flights?.map {
                    it.to!!.airportCode!!
                }?.let{
                    airportProvider.findAirportsByIATACode(it.plus(airport.iata!!))
                }

                flights?.map { flight ->
                    val fromStop = airports?.find{it.iata == flight.from!!.airportCode}

                    val to = RouteStop().apply {
                        val toStop = airports?.find{it.iata == flight.to!!.airportCode}
                        name = flight.to?.name
                        country = flight.to?.countryName
                        id = toStop?.id
                        latitude = toStop?.latitude
                        longitude = toStop?.longitude
                    }
                    val from = RouteStop().apply {
                        name = flight.from?.name
                        country = flight.from?.countryName
                        id = fromStop?.id
                        latitude = fromStop?.latitude
                        longitude = fromStop?.longitude
                    }
                    Route().apply {
                        this.to = to
                        this.from = from
                        this.type = RouteType.PLANE
                        this.durationTotal = flight.duration
                        this.durationMinutes = flight.duration?.rem(60)
                        this.durationHours = flight.duration?.div(60)
                    }
                } ?: listOf()
            } ?: listOf()
        return listOf(trainRoutes, flightRoutes).flatten().flatten()
    }
}