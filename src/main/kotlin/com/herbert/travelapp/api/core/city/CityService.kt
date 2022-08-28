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

    override fun updateCityStation(station: Station): Boolean {
        return try{
            station.id?.let { cityRepository.findCitiesByStationId(it) }?.forEach { city->
                cityRepository.saveCity(city.apply {
                    this.trainStations = this.trainStations?.map {
                        if(it.stationId == station.id){
                            it.apply {
                                this.apiId = station.apiId
                            }
                        }else{
                            it
                        }
                    }
                })
            }
            true
        }catch (ex: Exception){
            println(ex)
            false
        }
    }

    override fun findCityByShareId(shareId: String): City? {
        return cityRepository.findCityByShareId(shareId)
    }

    override fun findCitiesByAreaId(areaId: String): List<String>? {
        return cityRepository.findCitiesByAreaId(areaId)
    }

    override fun findCitiesByStationId(stationId: String): List<City>? {
        return cityRepository.findCitiesByStationId(stationId)
    }

    override fun findCitiesByAirportId(airportId: String): List<City>? {
        return cityRepository.findCitiesByAirportId(airportId)
    }
}