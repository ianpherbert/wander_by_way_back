package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindAllByName {
    fun findStationsByName(name: String): List<Station>
}
