package com.herbert.travelapp.api.core.station

interface StationProvider {
    fun findStationById(id: String) : Station?

    fun findStationsByParentId(parentId: String) : List<Station?>

    fun findStationsByUICId(uicId: String) : List<Station?>

    fun findStationByApiId(apiId: String) : Station?

    fun updateStationApiId(station: Station) : Station
}