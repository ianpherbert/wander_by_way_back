package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetByAreaId {
    fun findCitiesByAreaId(areaId: String): List<City>
}
