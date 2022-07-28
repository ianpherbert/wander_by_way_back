package com.herbert.travelapp.dataprovider.longTransport

import com.herbert.travelapp.dataprovider.flight.FlightSearchParams

class CityTravelData{
    var minStops: Int = 0
    var cityFrom: String = ""
    var results: Int = 0
    var countries: List<CountryFlights?> = listOf()
}

class CountryFlights{
    var name: String = ""
    var code: String = ""
    var results: Int = 0
    var flightsTo: List<Flight> = listOf()
}

class Flight{
    var kiwiId: String = ""
    var from : Location = Location()
    var to : Location = Location()
    var distance: Double? = null
    var duration: Double? = null
    var price: Int? = null
    var route: List<FlightStop>? = listOf()
    var localArrival: String = ""
    var utcArrival: String = ""
    var localDeparture: String = ""
    var utcDeparture: String = ""
}

class Location{
    var name: String = ""
    var airportCode: String = ""
    var countryName: String = ""
    var countryCode: String = ""
}

class FlightStop{
    var id: String = ""
    var flyFrom: String = ""
    var flyTo: String = ""
    var cityFrom: String = ""
    var cityTo: String = ""
    var cityCodeFrom: String = ""
    var cityCodeTo: String = ""
    var bagRecheck: Boolean? = null
    var airline: String = ""
    var localArrival: String = ""
    var utcArrival: String = ""
    var localDeparture: String = ""
    var utcDeparture: String = ""
    var layover: String? = null
}