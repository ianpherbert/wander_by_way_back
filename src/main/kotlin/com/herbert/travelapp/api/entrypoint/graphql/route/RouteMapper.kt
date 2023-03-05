package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.RouteOutput
import com.herbert.graphql.model.RouteSearchInput
import com.herbert.travelapp.api.core.route.PointSearchItem
import com.herbert.travelapp.api.core.route.Route
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RouteMapper {

    fun toRouteOutput(route: Route): RouteOutput

    fun toRouteOutputs(routes: List<Route>): List<RouteOutput>
    fun toPointSearchItem(routeSearchInput: RouteSearchInput): PointSearchItem
}
