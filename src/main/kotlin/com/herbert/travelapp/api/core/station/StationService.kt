package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.trainRoute.connector.TrainRouteFindStationApiId
import com.herbert.travelapp.api.core.station.connector.StationFindAllByApiIdIn
import com.herbert.travelapp.api.core.station.connector.StationFindAllByIdIn
import com.herbert.travelapp.api.core.station.connector.StationFindAllByName
import com.herbert.travelapp.api.core.station.connector.StationFindByApiId
import com.herbert.travelapp.api.core.station.connector.StationFindById
import com.herbert.travelapp.api.core.station.connector.StationUpdate
import com.herbert.travelapp.api.core.station.useCase.FindAllByApiIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindAllStationsByIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindStationByIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindStationsByApiIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindStationsByNameUseCase
import com.herbert.travelapp.api.core.station.useCase.UpdateStationApiIdUseCase
import com.herbert.travelapp.api.core.station.useCase.UpdateStationRoutesUseCase
import org.springframework.stereotype.Component

@Component
class StationService(
    val stationUpdate: StationUpdate,
    val stationFindById: StationFindById,
    val stationFindByApiId: StationFindByApiId,
    val stationFindAllByIdIn: StationFindAllByIdIn,
    val stationFindAllByName: StationFindAllByName,
    val stationFindAllByApiIdIn: StationFindAllByApiIdIn,
    val trainRouteFindStationApiId: TrainRouteFindStationApiId
) : FindAllByApiIdUseCase,
    FindAllStationsByIdUseCase,
    FindStationsByNameUseCase,
    FindStationsByApiIdUseCase,
    FindStationByIdUseCase,
    UpdateStationApiIdUseCase,
    UpdateStationRoutesUseCase {

    override fun updateStationApiId(station: Station): Station {
        return trainRouteFindStationApiId.findStationId(station)?.let {
            station.apply {
                this.apiId = it
            }.let {
                val stationUpdate = stationUpdate.updateStation(it)
                stationUpdate
            }
        } ?: station.apply {
            this.apiId = "INVALID"
        }.let {
            val stationUpdate = stationUpdate.updateStation(it)
            stationUpdate
        }
    }

    override fun findStationById(id: String): Station? {
        return stationFindById.findStationById(id)
    }

    override fun findStationByApiId(apiId: String): Station? {
        return stationFindByApiId.findStationByApiId(apiId)
    }

    override fun findStationsByName(name: String): List<Station> {
        return stationFindAllByName.findStationsByName(name)
    }

    override fun findAllStationsByIdIn(ids: List<String>): List<Station> {
        return stationFindAllByIdIn.findAllStationsByIdIn(ids)
    }

    override fun findAllByApiIdIn(apiIds: List<String>): List<Station> {
        return stationFindAllByApiIdIn.findAllByApiIdIn(apiIds)
    }

    override fun updateStationRoutes(station: Station, routes: List<Route>): Station {
        return stationUpdate.updateStation(
            station.apply {
                this.routes = routes
            }
        )
    }
}
