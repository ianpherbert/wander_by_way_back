package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface RouteStationApiIdFind {
    fun findStationId(station: Station): String?
}
