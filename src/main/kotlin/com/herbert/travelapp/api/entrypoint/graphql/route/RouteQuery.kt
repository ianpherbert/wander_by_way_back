package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.FindAllRoutesFromCityQueryResolver
import com.herbert.graphql.model.RouteSearchOutput
import com.herbert.travelapp.api.core.route.RouteProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class RouteQuery(
    val routeProvider: RouteProvider,
    val routeMapper: RouteMapper
) : FindAllRoutesFromCityQueryResolver {

    @QueryMapping
    override fun findAllRoutesFromCity(@Argument cityId: String): List<RouteSearchOutput> {
        return routeProvider.findAllRoutesFromCity(cityId)?.map {
            routeMapper.toRouteOutput(it)
        }?.groupBy { it.to.id }?.map {
            RouteSearchOutput().apply {
                this.routes = it.value
                this.destinationName = it.value.first().to.name
                this.destinationId = it.key
                this.latitude = it.value.first().to.latitude
                this.longitude = it.value.first().to.longitude
                this.durationAverage = it.value.map { it.durationTotal }.average().toInt()
                this.lineDistanceAverage = it.value.map { it.lineDistance }.average()
            }
        } ?: emptyList()
    }
}
