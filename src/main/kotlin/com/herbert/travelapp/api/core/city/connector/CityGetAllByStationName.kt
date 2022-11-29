package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetAllByStationName {
    fun findAllByStationName(name: String): List<City>
}
