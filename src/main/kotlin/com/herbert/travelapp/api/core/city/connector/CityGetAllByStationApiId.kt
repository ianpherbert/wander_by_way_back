package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetAllByStationApiId {
    fun findAllByStationApiId(apiId: String): List<City>
}
