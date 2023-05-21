package com.herbert.travelapp.api.core.airport

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.utils.Point

class Airport(
    var id: String,

    var location: AirportLocation,

    var name: String,

    var latitude: Double,

    var longitude: Double,

    var iata: String,

    var icao: String
) {

    var routes: List<Route> = listOf()
        get() = field.map { route ->
            route.apply {
                this.from = toRouteStop()
            }
        }

    private fun toRouteStop(): RouteStop {
        return RouteStop(
            name = this.name,
            id = this.id,
            latitude = this.latitude,
            longitude = this.longitude,
            country = this.location.country
        )
    }
    fun toPoint(): Point {
        return Point(
            latitude,
            longitude
        )
    }
}

class AirportLocation(
    var country: String,
    var region: String
)
