package com.herbert.travelapp.api.entrypoint.graphql.city

import com.herbert.graphql.model.CityOutput
import com.herbert.graphql.model.FindAllCitiesByAirportIdQueryResolver
import com.herbert.graphql.model.FindAllCitiesFromAssociatedTransitQueryResolver
import com.herbert.graphql.model.FindCityByIdQueryResolver
import com.herbert.graphql.model.SearchCityQueryResolver
import com.herbert.graphql.model.TransitSearchInput
import com.herbert.graphql.model.TransitType
import com.herbert.travelapp.api.core.city.CityProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CityQuery(
    val cityProvider: CityProvider,
    val cityMapper: CityMapper
) : SearchCityQueryResolver, FindCityByIdQueryResolver, FindAllCitiesByAirportIdQueryResolver, FindAllCitiesFromAssociatedTransitQueryResolver{

    @QueryMapping
    override fun findCityById(@Argument cityId: String): CityOutput? {
        return cityProvider.findCityById(cityId)?.let {
            cityMapper.toCityOutput(it)
        }
    }


    @QueryMapping
    override fun searchCity(@Argument query: String): List<CityOutput>? {
        return cityProvider.findCitiesByName(query)?.map{
            cityMapper.toCityOutput(it)
        }
    }

    @QueryMapping
    override fun findAllCitiesByAirportId(@Argument airportId: String): List<CityOutput>? {
        return cityProvider.findCitiesByAirportId(airportId)?.map{
            cityMapper.toCityOutput(it)
        }
    }

    @QueryMapping
    override fun findAllCitiesFromAssociatedTransit(@Argument transitSearchInput: TransitSearchInput): List<CityOutput> {
        val cities = when(transitSearchInput.transitType){
            TransitType.TRAIN -> cityProvider.findCitiesByStationId(transitSearchInput.id)
            TransitType.AIRPORT -> cityProvider.findCitiesByAirportId(transitSearchInput.id)
            else -> listOf()
        }
        return cities?.let {
            cityMapper.toCityOutputs(it)
        } ?: listOf()
    }
}