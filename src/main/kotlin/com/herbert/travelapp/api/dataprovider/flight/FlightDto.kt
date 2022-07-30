package com.herbert.travelapp.api.dataprovider.flight

class FlightSearchResult(
    val currency: String?,
    val all_airlines: List<String>?,
    val data: List<FlightSearchResultData>?
)

class FlightSearchResultData(
    val id: String?,
    val flyFrom: String?,
    val cityFrom: String?,
    val cityCodeFrom: String?,
    val cityTo: String?,
    val cityCodeTo: String?,
    val countryFrom: CountryResult?,
    val countryTo: CountryResult?,
    val quality: Double?,
    val distance: Double?,
    val duration: Map<String, String?>?,
    val price: Int?,
    val route: List<RouteStop>?,
    val booking_token: String?,
    val local_arrival: String?,
    val utc_arrival: String?,
    val local_departure: String?,
    val utc_departure: String?
)

class CountryResult(
    val code: String,
    val name: String,
)

class RouteStop(
    val id: String?,
    val flyFrom: String?,
    val flyTo: String?,
    val cityFrom: String?,
    val cityTo: String?,
    val cityCodeFrom: String?,
    val cityCodeTo: String?,
    val bags_recheck_required: Boolean?,
    val airline: String?,
    val operating_flight_no: String?,
    val local_arrival: String?,
    val utc_arrival: String?,
    val local_departure: String?,
    val utc_departure: String?
)

interface FlightRequestDto