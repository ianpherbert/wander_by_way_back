package com.herbert.travelapp.api.core.route

interface RouteProvider {
    fun findAllRoutesFromCity(cityId: String) : List<Route>?
}