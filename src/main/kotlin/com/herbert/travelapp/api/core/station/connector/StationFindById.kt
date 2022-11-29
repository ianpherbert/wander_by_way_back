package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindById {
    fun findStationById(id: String): Station?
}
