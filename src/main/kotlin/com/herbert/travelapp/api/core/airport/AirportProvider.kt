package com.herbert.travelapp.api.core.airport

interface AirportProvider {

    fun findAirportById(id: String) : Airport?

    fun findAirportsByName(name: String) : List<Airport>?

    fun findAirportByIATACode(iata: String) : Airport?

    fun findAirportByICAOCode(icao: String) : Airport?

    fun findAirportsByIATACode(iataCodes: List<String>) : List<Airport>?
}