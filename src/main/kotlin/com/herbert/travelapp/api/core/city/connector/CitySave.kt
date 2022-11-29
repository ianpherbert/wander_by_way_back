package com.herbert.travelapp.api.core.city.connector

import com.herbert.travelapp.api.core.city.City

interface CitySave {
    fun saveCity(city: City): City
}
