package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAirportByIATACodeUseCase {
    fun findAirportByIATACode(iata: String): Airport?
}
