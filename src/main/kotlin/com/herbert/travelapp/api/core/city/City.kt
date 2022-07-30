package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.station.Station


class City {

    var id : String? = null

    var areaId: List<String>? = null

    var name: String? = null

    var type: CityType? = null

    var population: Int? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null

    var airports : List<Airport>? = null

    var trainStations : List<Station>? = null

}

enum class CityType{
    MEGACITY,
    LARGECITY,
    MEDIUMCITY,
    SMALLCITY,
    TOWN,
    VILLAGE
}

class CityAirport{

    var name : String? = null

    var id : String? = null
}

class CityStation{
    var name : String? = null

    var id : String? = null

    var type : String? = null
}

enum class CityStationType{
    train,
    bus,
    ferry,
    other
}