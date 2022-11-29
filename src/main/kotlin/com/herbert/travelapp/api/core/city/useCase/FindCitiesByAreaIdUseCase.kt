package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindCitiesByAreaIdUseCase {
    fun findCitiesByAreaId(areaId: String): List<City>
}
