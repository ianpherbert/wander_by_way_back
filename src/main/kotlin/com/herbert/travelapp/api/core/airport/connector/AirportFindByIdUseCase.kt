package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindByIdUseCase {
    fun findAirportById(id: String): Airport?
}
