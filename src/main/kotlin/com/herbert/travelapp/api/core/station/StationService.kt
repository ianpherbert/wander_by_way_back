package com.herbert.travelapp.api.core.station

import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository,
    val findStationApiId: FindStationApiId,
) : StationProvider {

    override fun updateStationApiId(station: Station) : Station{
        return findStationApiId.findStationId(station)?.let{
            station.apply {
                this.apiId = it
            }.let{
                stationRepository.updateStation(it)
            }
        } ?: station.apply {
            this.apiId = "INVALID"
        }.let{
            stationRepository.updateStation(it)
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
}