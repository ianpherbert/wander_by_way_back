package com.herbert.travelapp.api.core.airport

import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
import com.herbert.travelapp.api.utils.Point


class Airport {

    var id: String? = null

    var location: AirportLocation? = null

    var name: String? = null

    var latitude: String? = null

    var longitude: String? = null

    var iata: String? = null

    var icao: String? = null

    fun toPoint() : Point{
        return Point(
            latitude!!.toDouble(),
            longitude!!.toDouble()
        )
    }

}

class AirportLocation{
    var country: String? = null

    var region: String? = null

    var cityList: List<AirportCity>? = null

}

class AirportCity{
    var cityId: String? = null

    var name: String? = null

    var slug: String? = null

    var type: CityTypeDB? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null
}