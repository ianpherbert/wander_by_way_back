package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindByCityIdUseCase {
    fun findCityById(id: String): City?
}
