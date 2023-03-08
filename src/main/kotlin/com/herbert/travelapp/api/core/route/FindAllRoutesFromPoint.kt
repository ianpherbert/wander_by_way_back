package com.herbert.travelapp.api.core.route

interface FindAllRoutesFromPoint {
    fun findAllRoutes(routeSearchItem: RouteSearchItem): List<Route>
}
