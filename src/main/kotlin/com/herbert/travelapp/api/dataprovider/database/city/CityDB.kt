package com.herbert.travelapp.api.dataprovider.database.city

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class CityDB {
    @Id
    var id : String? = null

    var areaId: List<String>? = null

    var name: String? = null

    var type: CityTypeDB? = null

    var population: Int? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null

    var airports : List<CityAirportDB>? = null

    var trainStations : List<CityStationDB>? = null
}

enum class CityTypeDB{
    MEGACITY,
    LARGECITY,
    MEDIUMCITY,
    SMALLCITY,
    TOWN,
    VILLAGE
}

class CityAirportDB{

    var name : String? = null

    var airportId : String? = null

}

class CityStationDB{
    var name : String? = null

    var stationId : String? = null

    var type : String? = null
}

enum class CityStationTypeDB{
    TRAIN,
    BUS,
    FERRY,
    OTHER
}