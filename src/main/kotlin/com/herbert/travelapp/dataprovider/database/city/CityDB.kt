package com.herbert.travelapp.dataprovider.database.city

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class CityDB {
    @Id
    var id : String? = null

    var areaId: List<String>? = null

    var name: String? = null

    var type: String? = null

    var population: Int? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null

    var airports : List<CityAirportDB>? = null

    var trainStations : List<CityStationDB>? = null
}

enum class CityTypeDB(val value: String){
    MEGACITY("megacity"),
    LARGECITY("largecity"),
    MEDIUMCITY("mediumcity"),
    SMALLCITY("smallcity"),
    TOWN("town"),
    VILLAGE("village")
}

class CityAirportDB{

    var name : String? = null

    var id : String? = null
}

class CityStationDB{
    var name : String? = null

    var id : String? = null

    var type : String? = null
}

enum class CityStationTypeDB(val value: String){
    TRAIN("train"),
    BUS("bus"),
    FERRY("ferry"),
    OTHER("other")
}