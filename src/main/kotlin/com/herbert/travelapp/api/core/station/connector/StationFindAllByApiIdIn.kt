package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindAllByApiIdIn {
    fun findAllByApiIdIn(apiIds: List<String>): List<Station>
}
