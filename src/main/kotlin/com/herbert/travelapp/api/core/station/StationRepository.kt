package com.herbert.travelapp.api.core.station

interface StationRepository {
    fun updateStation(station: Station) : Station
}