package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.FindAllRoutesQueryResolver
import com.herbert.graphql.model.RouteSearchInput
import com.herbert.graphql.model.RouteSearchOutput
import com.herbert.travelapp.api.core.route.FindAllRoutesFromPoint
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class RouteQuery(
    val findAllRoutesFromPoint: FindAllRoutesFromPoint,
    val routeMapper: RouteMapper
) : FindAllRoutesQueryResolver {

    @QueryMapping
    override fun findAllRoutes(@Argument searchInput: RouteSearchInput): List<RouteSearchOutput> {
        val pointSearchItem = routeMapper.toPointSearchItem(searchInput)
        return findAllRoutesFromPoint.findAllRoutes(pointSearchItem).let {
            routeMapper.toRouteOutputs(it)
        }.groupBy { it.to.id }.map {
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
}
