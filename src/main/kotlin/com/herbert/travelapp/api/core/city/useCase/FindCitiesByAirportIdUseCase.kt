package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindCitiesByAirportIdUseCase {
    fun findCitiesByAirportId(airportId: String): List<City>
}
