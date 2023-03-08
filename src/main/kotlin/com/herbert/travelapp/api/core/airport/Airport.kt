package com.herbert.travelapp.api.core.airport

import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
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
