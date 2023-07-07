package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.FindAllRoutesQueryResolver
import com.herbert.graphql.model.RouteSearchInput
import com.herbert.graphql.model.RouteSearchOutput
import com.herbert.travelapp.api.core.route.useCase.FindAllRoutesFromPointUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class RouteQuery(
    val findAllRoutesFromPointUseCase: FindAllRoutesFromPointUseCase,
    val routeMapper: RouteMapper
) : FindAllRoutesQueryResolver {

    @QueryMapping
    override fun findAllRoutes(@Argument searchInput: RouteSearchInput): List<RouteSearchOutput> {
        val pointSearchItem = routeMapper.toPointSearchItem(searchInput)
        return findAllRoutesFromPointUseCase.findAllRoutes(pointSearchItem).let {
            routeMapper.toRouteSearchOutput(it)
        }
    }
}
