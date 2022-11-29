package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAirportsByNameUseCase {
    fun findAirportsByName(name: String): List<Airport>?
}
