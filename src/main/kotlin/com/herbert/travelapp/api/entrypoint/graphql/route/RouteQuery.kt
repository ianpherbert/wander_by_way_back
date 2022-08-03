package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.FindAllRoutesFromCityQueryResolver
import com.herbert.graphql.model.RouteOutput
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
    override fun findAllRoutesFromCity(@Argument cityId: String): List<RouteOutput?> {
        return routeProvider.findAllRoutesFromCity(cityId)?.map{
            routeMapper.toRouteOutput(it)
        } ?: listOf()
    }
}