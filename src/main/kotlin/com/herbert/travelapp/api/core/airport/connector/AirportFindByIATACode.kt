package com.herbert.travelapp.api.core.airport.connector

import com.herbert.travelapp.api.core.airport.Airport

interface AirportFindByIATACode {
    fun findAirportByIATACode(iata: String): Airport?
}
