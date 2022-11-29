package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CitySearchByName {
    fun searchCitiesByName(name: String): List<City>?
}
