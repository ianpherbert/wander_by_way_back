package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.core.station.Station

interface CityProvider {
    fun findCityById(id: String) : City?

    fun findCityByShareId(shareId: String) : City?

    fun findCitiesByAreaId(areaId: String) : List<City>

    fun findCitiesByStationId(stationId: String) : List<City>?

    fun findCitiesByAirportId(airportId: String) : List<City>?

    fun findCitiesByName(name: String) : List<City>?

    fun updateCityStation(station: Station) : Boolean

}