package com.herbert.travelapp.api.core.airport


class Airport {

    var id: String? = null

    var location: AirportLocation? = null

    var name: String? = null

    var latitude: String? = null

    var longitude: String? = null

    var iata: String? = null

    var icao: String? = null

}

class AirportLocation{
    var country: String? = null

    var region: String? = null

    var cities: List<String>? = null

}