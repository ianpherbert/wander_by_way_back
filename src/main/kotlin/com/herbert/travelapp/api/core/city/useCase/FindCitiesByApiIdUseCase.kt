package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindCitiesByApiIdUseCase {
    fun findCitiesByApiId(stationId: String, name: String): List<City>?
}
