package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindAllById {
    fun findAllAirportsByIdIn(ids: List<String>): List<Airport>
}
