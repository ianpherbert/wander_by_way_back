package com.herbert.travelapp.api.core.flight


class Flight{
    var kiwiId: String? = ""
    var from : FlightLocation? = FlightLocation()
    var to : FlightLocation? = FlightLocation()
    var distance: Double? = null
    var duration: Int? = null
    var price: Int? = null
    var route: List<FlightStop>? = listOf()
    var localArrival: String? = ""
    var utcArrival: String? = ""
    var localDeparture: String? = ""
    var utcDeparture: String? = ""
}

class FlightLocation{
    var name: String? = ""
    var airportCode: String? = ""
    var countryName: String? = ""
    var countryCode: String? = ""
}

class FlightStop{
    var id: String? = ""
    var flyFrom: String? = ""
    var flyTo: String? = ""
    var cityFrom: String? = ""
    var cityTo: String? = ""
    var cityCodeFrom: String? = ""
    var cityCodeTo: String? = ""
    var bagRecheck: Boolean? = null
    var airline: String? = ""
    var localArrival: String? = ""
    var utcArrival: String? = ""
    var localDeparture: String? = ""
    var utcDeparture: String? = ""
    var layover: String? = null
}