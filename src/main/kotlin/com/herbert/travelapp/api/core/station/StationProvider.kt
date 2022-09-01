package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.trainRoute.TrainRoute

interface StationProvider {
    fun findStationById(id: String) : Station?

    fun findStationsByParentId(parentId: String) : List<Station?>

    fun findStationsByUICId(uicId: String) : List<Station?>

    fun findStationByApiId(apiId: String) : Station?

    fun updateStationApiId(station: Station) : Station

    fun findStationsByName(name: String) : List<Station?>

    fun searchStationsByName(name: String) : List<Station>?

    fun findAllStationsByIdIn(ids: List<String>) : List<Station>

    fun findAllByApiIdIn(apiIds: List<String>) : List<Station>

    fun updateNonExistantApiIds(routes: List<TrainRoute>)
}