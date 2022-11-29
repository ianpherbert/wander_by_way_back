package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindByICAOCode {
    fun findAirportByICAOCode(icao: String): Airport?
}
