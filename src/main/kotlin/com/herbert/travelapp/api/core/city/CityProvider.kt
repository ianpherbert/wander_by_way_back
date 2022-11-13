package com.herbert.travelapp.api.core.city

interface CityProvider {
    fun findCityById(id: String): City?

    fun findCityByShareId(shareId: String): City?

    fun findCitiesByAreaId(areaId: String): List<City>

    fun findCitiesByApiId(stationId: String, name: String): List<City>?

    fun findCitiesByAirportId(airportId: String): List<City>?

    fun findCitiesByName(name: String): List<City>?
}
