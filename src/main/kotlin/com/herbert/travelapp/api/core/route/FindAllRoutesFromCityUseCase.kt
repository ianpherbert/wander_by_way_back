package com.herbert.travelapp.api.core.route

interface FindAllRoutesFromCityUseCase {
    fun findAllRoutesFromCity(cityId: String): List<Route>
}
