package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.core.station.Station
import org.springframework.stereotype.Component

@Component
class CityService(
    val cityRepository: CityRepository
) : CityProvider {
    override fun findCityById(id: String): City? {
        return cityRepository.getCityById(id)
    }

    override fun findCitiesByName(name: String): List<City>? {
        return cityRepository.searchCitiesByName(name)
    }

    override fun findCityByShareId(shareId: String): City? {
        return cityRepository.findCityByShareId(shareId)
    }

    override fun findCitiesByAreaId(areaId: String): List<City> {
        return cityRepository.findCitiesByAreaId(areaId)
    }

    override fun findCitiesByApiId(stationApiId: String, name: String): List<City>? {
        val byApiId = cityRepository.findAllByStationApiId(stationApiId)
        return if(byApiId.isEmpty()){
            cityRepository.findAllByStationName(name)
        }else{
            byApiId
        }
    }

    override fun findCitiesByAirportId(airportId: String): List<City>? {
        return cityRepository.findCitiesByAirportId(airportId)
    }
}