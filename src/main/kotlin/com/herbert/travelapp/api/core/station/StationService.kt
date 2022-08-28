package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.city.CityRepository
import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository,
    val findStationApiId: FindStationApiId,
    val cityProvider: CityProvider
) : StationProvider {

    override fun updateStationApiId(station: Station) : Station{
        return findStationApiId.findStationId(station)?.let{
            station.apply {
                this.apiId = it
            }.let{
                val stationUpdate = stationRepository.updateStation(it)
                cityProvider.updateCityStation(stationUpdate)
                stationUpdate
            }
        } ?: station.apply {
            this.apiId = "INVALID"
        }.let{
            val stationUpdate = stationRepository.updateStation(it)
            cityProvider.updateCityStation(stationUpdate)
            stationUpdate
        }
    }
    override fun findStationById(id: String): Station? {
        return stationRepository.findStationById(id)
    }

    override fun findStationsByParentId(parentId: String): List<Station?> {
        return stationRepository.findStationsByParentId(parentId)
    }

    override fun findStationsByUICId(uicId: String): List<Station?> {
        return stationRepository.findStationsByUICId(uicId)
    }

    override fun findStationByApiId(apiId: String): Station? {
        return stationRepository.findStationByApiId(apiId)
    }

    override fun findStationsByName(name: String) : List<Station?>{
        return stationRepository.findStationsByName(name)
    }

    override fun searchStationsByName(name: String): List<Station>? {
        return stationRepository.searchStationsByName(name)
    }

    override fun findAllStationsByIdIn(ids: List<String>): List<Station> {
        return stationRepository.findAllStationsByIdIn(ids)
    }


}