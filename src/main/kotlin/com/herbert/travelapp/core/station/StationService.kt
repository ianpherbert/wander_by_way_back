package com.herbert.travelapp.core.station

import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository
) : StationProvider {
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