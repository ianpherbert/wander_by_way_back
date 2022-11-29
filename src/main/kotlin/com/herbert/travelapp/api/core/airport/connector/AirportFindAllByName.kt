package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindAllByName {
    fun findAirportsByName(name: String): List<Airport>
}
