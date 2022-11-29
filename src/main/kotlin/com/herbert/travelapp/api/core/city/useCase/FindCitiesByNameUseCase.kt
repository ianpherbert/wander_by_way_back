package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindCitiesByNameUseCase {
    fun findCitiesByName(name: String): List<City>?
}
