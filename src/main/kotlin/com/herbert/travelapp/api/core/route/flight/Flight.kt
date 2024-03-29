package com.herbert.travelapp.api.core.route.flight

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.core.route.RouteType

class Flight {
    var kiwiId: String? = ""
    var from: FlightLocation? = FlightLocation()
    var to: FlightLocation? = FlightLocation()
    var distance: Double? = null
    var duration: Int? = null
    var price: Int? = null
    var route: List<FlightStop>? = listOf()
    var localArrival: String? = ""
    var utcArrival: String? = ""
    var localDeparture: String? = ""
    var utcDeparture: String? = ""

    fun toRoute(toStop: Airport, fromStop: Airport): Route {
        val to = RouteStop(
            name = to?.name ?: "",
            country = to?.countryName  ?: "",
            id = toStop.id,
            latitude = toStop.latitude,
            longitude = toStop.longitude,
            iata = toStop.iata
        )
        val from = RouteStop(
            name = from?.name ?: "",
            country = from?.countryName ?: "",
            id = fromStop.id,
            latitude = fromStop.latitude,
            longitude = fromStop.longitude,
            iata = fromStop.iata
        )
        return Route(
            to = to,
            type = RouteType.PLANE,
            durationTotal = duration ?: 0,
            durationMinutes = duration?.rem(60) ?: 0,
            durationHours = duration?.div(60) ?: 0
        ).apply {
            this.from = from
        }
    }
}

class FlightLocation {
    var name: String? = ""
    var airportCode: String? = ""
    var countryName: String? = ""
    var countryCode: String? = ""
}

class FlightStop {
    var id: String? = ""
    var flyFrom: String? = ""
    var flyTo: String? = ""
    var cityFrom: String? = ""
    var cityTo: String? = ""
    var cityCodeFrom: String? = ""
    var cityCodeTo: String? = ""
    var bagRecheck: Boolean? = null
    var airline: String? = ""
    var localArrival: String? = ""
    var utcArrival: String? = ""
    var localDeparture: String? = ""
    var utcDeparture: String? = ""
    var layover: String? = null
}
