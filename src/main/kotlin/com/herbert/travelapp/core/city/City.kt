package com.herbert.travelapp.core.city

import com.herbert.travelapp.core.airport.Airport
import com.herbert.travelapp.core.station.Station
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


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

//    var airportObjects : List<Airport>? = null

    var trainStations : List<Station>? = null

//    var stationObjects : List<Station>? = null
}

enum class CityType(val value: String){
    MEGACITY("megacity"),
    LARGECITY("largecity"),
    MEDIUMCITY("mediumcity"),
    SMALLCITY("smallcity"),
    TOWN("town"),
    VILLAGE("village")
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

enum class CityStationType(val value: String){
    TRAIN("train"),
    BUS("bus"),
    FERRY("ferry"),
    OTHER("other")
}