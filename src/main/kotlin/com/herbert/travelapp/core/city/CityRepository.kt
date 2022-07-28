package com.herbert.travelapp.core.city

interface CityRepository {
    fun getCityById(id: String) : City?
}