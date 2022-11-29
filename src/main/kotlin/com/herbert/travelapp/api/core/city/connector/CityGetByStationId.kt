package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetByStationId {
    fun findCitiesByStationId(stationId: String): List<City>?
}
