package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindAllByIACOCode {
    fun findAirportsByIACOCode(codes: List<String>): List<Airport>?
}
