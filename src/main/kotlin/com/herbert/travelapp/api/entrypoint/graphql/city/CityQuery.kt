package com.herbert.travelapp.api.entrypoint.graphql.city

import com.herbert.graphql.model.CityOutput
import com.herbert.graphql.model.CitySearchOutput
import com.herbert.graphql.model.FindCityByIdQueryResolver
import com.herbert.graphql.model.SearchCityQueryResolver
import com.herbert.travelapp.api.core.city.CityProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CityQuery(
    val cityProvider: CityProvider,
    val cityMapper: CityMapper
) : SearchCityQueryResolver, FindCityByIdQueryResolver{

    @QueryMapping
    override fun findCityById(@Argument cityId: String): CityOutput? {
        return cityProvider.findCityById(cityId)?.let {
            cityMapper.toCityOutput(it)
        }
    }


    @QueryMapping
    override fun searchCity(@Argument query: String): List<CitySearchOutput>? {
        return cityProvider.findCitiesByName(query)?.map{
            cityMapper.toCitySearchOutput(it)
        }
    }
}