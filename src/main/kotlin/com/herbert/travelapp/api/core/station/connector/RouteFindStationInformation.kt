package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface RouteFindStationInformation {
    fun findStationInformation(apiId: String): Station?
}
