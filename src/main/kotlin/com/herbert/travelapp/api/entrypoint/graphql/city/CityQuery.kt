package com.herbert.travelapp.api.entrypoint.graphql.city

import com.herbert.graphql.model.CityOutput
import com.herbert.graphql.model.CitySearchOutput
import com.herbert.graphql.model.FindCityQueryResolver
import com.herbert.graphql.model.SearchCityQueryResolver
import com.herbert.travelapp.api.core.city.CityProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller

@Controller
class CityQuery(
    val cityProvider: CityProvider
) : SearchCityQueryResolver, FindCityQueryResolver{
    override fun findCity(@Argument cityId: String): CityOutput {
        TODO("Not yet implemented")
    }

    override fun searchCity(@Argument query: String): MutableList<CitySearchOutput> {
        TODO("Not yet implemented")
    }
}