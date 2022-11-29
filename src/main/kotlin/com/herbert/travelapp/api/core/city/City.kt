package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.dataprovider.database.station.StationDBType
import com.herbert.travelapp.api.utils.Point

class City {

    var id: String? = null

    var areaId: List<String>? = null

    var name: String? = null

    var type: CityType? = null

    var population: Int? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null

    var airports: List<CityAirport>? = null

    var trainStations: List<CityStation>? = null

    fun toPoint(): Point {
        return Point(
            latitude!!.toDouble(),
            longitude!!.toDouble()
        )
    }

    fun getStationIds(): List<String> {
        return this.trainStations?.mapNotNull { it.stationId } ?: listOf()
    }

    fun getAirportIds(): List<String> {
        return this.airports?.mapNotNull { it.airportId } ?: listOf()
    }

    fun getAirportIATACodes(): List<String> {
        return this.airports?.mapNotNull { it.iata } ?: listOf()
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
    train,
    bus,
    ferry,
    other
}

class CityAirport {
    var name: String? = null

    var airportId: String? = null

    var icao: String? = null

    var iata: String? = null

    var latitude: String? = null

    var longitude: String? = null
}

class CityStation {
    var name: String? = null

    var stationId: String? = null

    var type: StationDBType? = null

    var latitude: String? = null

    var longitude: String? = null

    var apiId: String? = null

    var main: Boolean? = null

    var airport: Boolean? = null
}
