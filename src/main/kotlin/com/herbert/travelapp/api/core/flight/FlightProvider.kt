package com.herbert.travelapp.api.core.flight

interface FlightProvider {
    fun findAllRoutesFromAirport(airportCode: String, date: String, toDate: String) : List<Flight?>

    fun findRoutesBetweenCities(fromAirportCode: String, toAirportCode: String, fromDate: String, toDate: String): List<Flight?>
}