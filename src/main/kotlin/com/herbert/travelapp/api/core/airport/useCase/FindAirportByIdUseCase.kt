package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAirportByIdUseCase {
    fun findAirportById(id: String): Airport?
}
