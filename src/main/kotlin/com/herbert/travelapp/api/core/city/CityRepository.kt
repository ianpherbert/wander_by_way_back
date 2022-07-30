package com.herbert.travelapp.api.core.city

interface CityRepository {
    fun getCityById(id: String) : City?
}