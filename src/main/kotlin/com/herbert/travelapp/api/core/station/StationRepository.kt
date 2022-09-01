package com.herbert.travelapp.api.core.station

interface StationRepository {
    fun updateStation(station: Station) : Station

    fun findStationById(id: String): Station?

    fun findStationsByParentId(parentId: String): List<Station?>

    fun findStationsByUICId(uicId: String): List<Station?>

    fun findStationByApiId(apiId: String): Station?

    fun findStationsByName(name: String) : List<Station?>

    fun searchStationsByName(name: String) : List<Station>?

    fun findAllStationsByIdIn(ids: List<String>) : List<Station>

    fun findAllByApiIdIn(apiIds: List<String>) : List<Station>

    fun findAllBySlugContaining(fragment: String) : List<Station>

    fun saveStation(station: Station) : Station
}