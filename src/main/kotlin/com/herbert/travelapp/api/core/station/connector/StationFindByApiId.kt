package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindByApiId {
    fun findStationByApiId(apiId: String): Station?
}
