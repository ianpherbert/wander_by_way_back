package com.herbert.travelapp.api.core.route

interface FindAllRoutesFromPoint {
    fun findAllRoutes(pointSearchItem: PointSearchItem): List<Route>
}
