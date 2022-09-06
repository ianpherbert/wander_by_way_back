package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.trainRoute.TrainRoute
import com.herbert.travelapp.api.extensions.toSearchableName
import com.herbert.travelapp.api.utils.DistanceCalculator
import com.herbert.travelapp.api.utils.Point
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository,
    val findStationApiId: FindStationApiId,
    val cityProvider: CityProvider
) : StationProvider {

    override fun updateStationApiId(station: Station): Station {
        return findStationApiId.findStationId(station)?.let {
            station.apply {
                this.apiId = it
            }.let {
                val stationUpdate = stationRepository.updateStation(it)
                cityProvider.updateCityStation(stationUpdate)
                stationUpdate
            }
        } ?: station.apply {
            this.apiId = "INVALID"
        }.let {
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

    private fun findAndUpdateStationFromApi(route: TrainRoute): Boolean {
        val toStationSlug = route.toStationName?.toSearchableName() ?: return false
        var update = false
        stationRepository.findAllBySlugContaining(toStationSlug).forEach { station ->
            if (route.latitude != null && station.latitude != null) {
                val distance = DistanceCalculator(
                    Point(route.latitude!!.toDouble(), route.longitude!!.toDouble()),
                    Point(station.latitude!!.toDouble(), station.longitude!!.toDouble())
                ).distance('K')
                if (distance < 5 ) {
                    update = updateStationApiId(station, route.toStationId)
                }
            } else if(station.slug!!.toSearchableName() == route.toStationName!!.toSearchableName()) {
                    update = updateStationApiId(station, route.toStationId)
            }
        }
        return update
    }

    private fun updateStationApiId(station: Station, apiId: String?) : Boolean{
        return try {
            station.apply {
                this.apiId = apiId
            }.let {
                stationRepository.saveStation(it)
                cityProvider.updateCityStation(it)
                println("${station.slug} update with ${apiId} apiId")
            }
            true
        }catch (ex: Exception){
            println(ex.message)
            false
        }
    }

    override fun updateNonExistantApiIds(routes: List<TrainRoute>){
        routes.mapNotNull { it.toStationId }.let {
            //list of apiIds not attached to a station
            val stations = findAllByApiIdIn(it).map { it.apiId }
            //all stations that contain one of the apiIds in the above list
            //v-- routes whose toStationId is not contained in the list of stations --v
            routes.filter { !stations.contains(it.toStationId) }.forEach {
                //find the station by its apiId and update the entry in the DB
                findAndUpdateStationFromApi(it)
            }
        }
    }

}