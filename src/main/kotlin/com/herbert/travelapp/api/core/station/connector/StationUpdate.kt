package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationUpdate {
    fun updateStation(station: Station): Station
}
