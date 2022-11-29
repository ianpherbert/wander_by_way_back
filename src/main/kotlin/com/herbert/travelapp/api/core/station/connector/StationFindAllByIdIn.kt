package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindAllByIdIn {
    fun findAllStationsByIdIn(ids: List<String>): List<Station>
}
