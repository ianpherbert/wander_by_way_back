package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CityGetAllByIataCodeOrAirportId {
    fun getByAirportIdOrIata(identifier: String): List<City>
}
