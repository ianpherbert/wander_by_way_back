package com.herbert.travelapp.api.dataprovider.longTransport

interface RouteFinder {
    fun findAllRoutesFromAirport(airportCode: String, date: String, toDate: String) : CityTravelData?

    fun findRoutesBetweenCities(fromAirportCode: String, toAirportCode: String, fromDate: String, toDate: String): CityTravelData?
}