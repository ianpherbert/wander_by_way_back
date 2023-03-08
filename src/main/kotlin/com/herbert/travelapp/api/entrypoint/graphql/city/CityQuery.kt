package com.herbert.travelapp.api.entrypoint.graphql.city

import com.herbert.graphql.model.CityOutput
import com.herbert.graphql.model.FindAllCitiesByAirportIdQueryResolver
import com.herbert.graphql.model.FindAllCitiesFromAssociatedTransitQueryResolver
import com.herbert.graphql.model.FindCityByIdQueryResolver
import com.herbert.graphql.model.SearchCityQueryResolver
import com.herbert.graphql.model.StationType
import com.herbert.graphql.model.TransitSearchInput
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAirportIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByApiIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByNameUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CityQuery(
    val findByCityIdUseCase: FindByCityIdUseCase,
    val findCitiesByNameUseCase: FindCitiesByNameUseCase,
    val findCitiesByAirportIdUseCase: FindCitiesByAirportIdUseCase,
    val findCitiesByApiIdUseCase: FindCitiesByApiIdUseCase,
    val cityMapper: CityMapper
) : SearchCityQueryResolver, FindCityByIdQueryResolver, FindAllCitiesByAirportIdQueryResolver, FindAllCitiesFromAssociatedTransitQueryResolver {

    @QueryMapping
    override fun findCityById(@Argument cityId: String): CityOutput? {
        return findByCityIdUseCase.findCityById(cityId)?.let {
            cityMapper.toCityOutput(it)
        }
    }

    @QueryMapping
    override fun searchCity(@Argument query: String): List<CityOutput>? {
        return if (query == "") {
            emptyList()
        } else {
            findCitiesByNameUseCase.findCitiesByName(query).map {
                cityMapper.toCityOutput(it)
            }
        }
    }

    @QueryMapping
    override fun findAllCitiesByAirportId(@Argument airportId: String): List<CityOutput>? {
        return findCitiesByAirportIdUseCase.findCitiesByAirportId(airportId).map {
            cityMapper.toCityOutput(it)
        }
    }

    @QueryMapping
    override fun findAllCitiesFromAssociatedTransit(@Argument transitSearchInput: TransitSearchInput): List<CityOutput> {
        val cities = when (transitSearchInput.transitType) {
            StationType.TRAIN -> findCitiesByApiIdUseCase.findCitiesByApiId(
                transitSearchInput.id,
                transitSearchInput.name
            )
            StationType.AIRPORT -> findCitiesByAirportIdUseCase.findCitiesByAirportId(transitSearchInput.id)
            else -> listOf()
        }
        return cities.let {
            cityMapper.toCityOutputs(it)
        }
    }
}
