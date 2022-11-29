package com.herbert.travelapp.api.core.airport

interface AirportRepository {
    fun findAirportById(id: String): Airport?

    fun findAirportsByName(name: String): List<Airport>?

    fun findAirportByIATACode(iata: String): Airport?

    fun findAirportByICAOCode(icao: String): Airport?

    fun findAirportsByIACOCode(codes: List<String>): List<Airport>?

    fun findAllAirportsByIdIn(ids: List<String>): List<Airport>
}
