package com.herbert.travelapp.api.core.city

interface CityRepository {
    fun getCityById(id: String): City?

    fun searchCitiesByName(name: String): List<City>?

    fun findCityByShareId(shareId: String): City?

    fun findCitiesByAreaId(areaId: String): List<City>

    fun findCitiesByStationId(stationId: String): List<City>?

    fun findCitiesByAirportId(airportId: String): List<City>?

    fun saveCity(city: City): City

    fun findAllByStationApiId(apiId: String): List<City>

    fun findAllByStationName(name: String): List<City>
}
