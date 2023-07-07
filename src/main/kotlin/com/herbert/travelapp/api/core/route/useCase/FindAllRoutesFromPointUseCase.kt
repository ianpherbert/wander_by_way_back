package com.herbert.travelapp.api.core.route.useCase

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteSearchItem

interface FindAllRoutesFromPointUseCase {
    fun findAllRoutes(routeSearchItem: RouteSearchItem): List<Route>
}
