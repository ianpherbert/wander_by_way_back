package com.herbert.travelapp.api.entrypoint.graphql.airport

import com.herbert.graphql.model.AirportOutput
import com.herbert.graphql.model.FindAirportByIdQueryResolver
import com.herbert.graphql.model.SearchAirportQueryResolver
import com.herbert.travelapp.api.core.airport.connector.AirportFindByIdUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByNameUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AirportQuery(
    val airportFindByIdUseCase: AirportFindByIdUseCase,
    val findAirportsByNameUseCase: FindAirportsByNameUseCase,
    val airportMapper: AirportMapper
) : SearchAirportQueryResolver, FindAirportByIdQueryResolver {

    @QueryMapping
    override fun findAirportById(@Argument airportId: String): AirportOutput? {
        return airportFindByIdUseCase.findAirportById(airportId)?.let {
            airportMapper.toAiportOutput(it)
        }
    }

    @QueryMapping
    override fun searchAirport(@Argument query: String): List<AirportOutput>? {
        return findAirportsByNameUseCase.findAirportsByName(query).map {
            airportMapper.toAiportOutput(it)
        }
    }
}
