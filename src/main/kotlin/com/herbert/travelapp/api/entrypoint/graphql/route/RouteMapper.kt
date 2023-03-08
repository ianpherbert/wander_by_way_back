package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.RouteOutput
import com.herbert.graphql.model.RouteSearchInput
import com.herbert.graphql.model.RouteSearchOutput
import com.herbert.travelapp.api.core.route.RouteSearchItem
import com.herbert.travelapp.api.core.route.Route
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class RouteMapper {
    abstract fun toRouteOutput(route: Route): RouteOutput

    fun toRouteSearchOutput(routes: List<Route>): List<RouteSearchOutput> {
        val routeMapperImpl = RouteMapperImpl()
        return routes.map { routeMapperImpl.toRouteOutput(it) }.groupBy { it.to.id }.map {
            RouteSearchOutput().apply {
                this.routes = it.value
                this.destinationName = it.value.first().to.name
                this.destinationId = it.key
                this.latitude = it.value.first().to.latitude
                this.longitude = it.value.first().to.longitude
                this.durationAverage = it.value.map { it.durationTotal }.average().toInt()
                this.lineDistanceAverage = it.value.map { it.lineDistance }.average()
            }
        }
    }

    abstract fun toPointSearchItem(routeSearchInput: RouteSearchInput): RouteSearchItem
}
