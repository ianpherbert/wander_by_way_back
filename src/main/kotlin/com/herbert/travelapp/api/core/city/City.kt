package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.dataprovider.database.station.StationDBType
import com.herbert.travelapp.api.utils.Point

class City(
    var name: String,

    var type: CityType,

    var population: Int,

    var latitude: Double,

    var longitude: Double,

    var country: String,

    var shareId: String,

    var id: String,

    var areaId: List<String> = listOf(),

    var airports: List<CityAirport> = listOf(),

    var trainStations: List<CityStation> = listOf()
) {

    fun toPoint(): Point {
        return Point(
            latitude,
            longitude
        )
    }

    fun getStationIds(): List<String> {
        return this.trainStations.map { it.stationId }
    }

    fun getAirportIds(): List<String> {
        return this.airports.map { it.airportId }
    }

    fun getAirportIATACodes(): List<String> {
        return this.airports.map { it.iata }
    }
}

enum class CityType {
    MEGACITY,
    LARGECITY,
    MEDIUMCITY,
    SMALLCITY,
    TOWN,
    VILLAGE
}

enum class CityStationType {
    TRAIN,
    BUS,
    FERRY,
    OTHER
}

class CityAirport(
    var name: String,

    var airportId: String,

    var icao: String,

    var iata: String,

    var latitude: String,

    var longitude: String
)

class CityStation(
    var name: String,

    var stationId: String,

    var type: StationDBType,

    var latitude: String,

    var longitude: String,

    var apiId: String
)
