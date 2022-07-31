package com.herbert.travelapp.api.core.city

import org.springframework.stereotype.Component

@Component
class CityService(
    val cityRepository: CityRepository
) : CityProvider {
    override fun findCityById(id: String): City? {
        return cityRepository.getCityById(id)
    }

    override fun findCitiesByName(name: String): List<City>? {
        return cityRepository.getCitiesByName(name)
    }

    override fun findCityByShareId(shareId: String): City? {
        TODO("Not yet implemented")
    }

    override fun findCitiesByAreaId(areaId: String): List<String?> {
        TODO("Not yet implemented")
    }

    override fun findCitiesByStationId(stationId: String): List<City?> {
        TODO("Not yet implemented")
    }

    override fun findCitiesByAirportId(airportId: String): List<City?> {
        TODO("Not yet implemented")
    }
}