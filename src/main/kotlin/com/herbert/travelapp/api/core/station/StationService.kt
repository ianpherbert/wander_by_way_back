package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.trainRoute.TrainRoute
import com.herbert.travelapp.api.extensions.toSearchableName
import com.herbert.travelapp.api.utils.DistanceCalculator
import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository,
    val findStationApiId: FindStationApiId,
    val cityProvider: CityProvider
) : StationProvider, UpdateStationRoutes {

    override fun updateStationApiId(station: Station): Station {
        return findStationApiId.findStationId(station)?.let {
            station.apply {
                this.apiId = it
            }.let {
                val stationUpdate = stationRepository.updateStation(it)
                stationUpdate
            }
        } ?: station.apply {
            this.apiId = "INVALID"
        }.let {
            val stationUpdate = stationRepository.updateStation(it)
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

    override fun findStationsByName(name: String): List<Station?> {
        return stationRepository.findStationsByName(name)
    }

    override fun searchStationsByName(name: String): List<Station>? {
        return stationRepository.searchStationsByName(name)
    }

    override fun findAllStationsByIdIn(ids: List<String>): List<Station> {
        return stationRepository.findAllStationsByIdIn(ids)
    }

    override fun findAllByApiIdIn(apiIds: List<String>): List<Station> {
        return stationRepository.findAllByApiIdIn(apiIds)
    }

    override fun updateStationRoutes(station: Station, routes: List<Route>): Station {
        return stationRepository.updateStation(
            station.apply {
                this.routes = routes
            }
        )
    }
}
