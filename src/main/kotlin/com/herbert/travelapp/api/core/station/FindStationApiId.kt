package com.herbert.travelapp.api.core.station

interface FindStationApiId{
    fun findStationId(station: Station) : String?
}