package com.herbert.travelapp.api.entrypoint.graphql.route

import com.herbert.graphql.model.FindAllRoutesFromCityQueryResolver
import com.herbert.graphql.model.RouteOutput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping

class RouteQuery(

) : FindAllRoutesFromCityQueryResolver {

    @QueryMapping
    override fun findAllRoutesFromCity(@Argument cityId: String): MutableList<RouteOutput?> {
        TODO("Not yet implemented")
    }
}