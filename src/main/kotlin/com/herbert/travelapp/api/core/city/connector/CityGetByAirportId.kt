package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetByAirportId {
    fun findCitiesByAirportId(airportId: String): List<City>
}
