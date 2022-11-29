package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAllAirportsByIdInUseCase {
    fun findAllAirportsByIdIn(ids: List<String>): List<Airport>
}
