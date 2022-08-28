package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.dataprovider.database.station.StationDBType


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

    var airports : List<CityAirport>? = null

    var trainStations : List<CityStation>? = null

    fun getStationIds() : List<String>{
        return this.trainStations?.mapNotNull { it.stationId } ?: listOf()
    }

    fun getAirportIds() : List<String>{
        return this.airports?.mapNotNull { it.airportId } ?: listOf()
    }

    fun updateStationApiId(station: Station){
        this.trainStations = this.trainStations?.map {
            if(it.stationId == station.id){
                it.apply {
                    this.apiId = station.apiId
                }
            }else{
                it
            }
        }
    }
}

enum class CityType{
    MEGACITY,
    LARGECITY,
    MEDIUMCITY,
    SMALLCITY,
    TOWN,
    VILLAGE
}

enum class CityStationType{
    train,
    bus,
    ferry,
    other
}

class CityAirport{
    var name : String? = null

    var airportId : String? = null

    var icao: String? = null

    var iata: String? = null

    var latitude: String? = null

    var longitude: String? = null
}

class CityStation{
    var name : String? = null

    var stationId : String? = null

    var type : StationDBType? = null

    var latitude: String? = null

    var longitude: String? = null

    var apiId: String? = null

    var main: Boolean? = null

    var airport: Boolean? = null
}