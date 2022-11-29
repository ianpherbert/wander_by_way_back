package com.herbert.travelapp.api.core.city.useCase

import com.herbert.travelapp.api.core.city.City

interface FindByShareIdUseCase {
    fun findCityByShareId(shareId: String): City?
}
