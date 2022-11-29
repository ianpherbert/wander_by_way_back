package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAirportsByIATACodeUseCase {
    fun findAirportsByIATACode(iataCodes: List<String>): List<Airport>?
}
