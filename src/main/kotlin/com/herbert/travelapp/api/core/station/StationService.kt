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
        TODO("Not yet implemented")
    }

    override fun findStationsByParentId(parentId: String): List<Station?> {
        TODO("Not yet implemented")
    }

    override fun findStationsByUICId(uicId: String): List<Station?> {
        TODO("Not yet implemented")
    }

    override fun findStationByApiId(apiId: String): Station? {
        TODO("Not yet implemented")
    }
}