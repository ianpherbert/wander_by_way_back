package com.herbert.travelapp.api.core.airport.useCase

import com.herbert.travelapp.api.core.airport.Airport

interface FindAirportByICAOCodeUseCase {
    fun findAirportByICAOCode(icao: String): Airport?
}
