package com.herbert.travelapp.dataprovider.flight

import java.util.Date

interface FlightDateFormatter{
    fun formatDate(date: Date) : String
}

interface FlightSearcher {
    fun findFlight(searchParams: FlightSearchParams) : FlightSearchResult?
}