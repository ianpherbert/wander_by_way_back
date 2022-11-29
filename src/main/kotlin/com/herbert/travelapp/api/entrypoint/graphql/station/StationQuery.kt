package com.herbert.travelapp.api.entrypoint.graphql.station

import com.herbert.graphql.model.FindStationByIdQueryResolver
import com.herbert.graphql.model.SearchStationQueryResolver
import com.herbert.graphql.model.StationOutput
import com.herbert.travelapp.api.core.station.useCase.FindStationByIdUseCase
import com.herbert.travelapp.api.core.station.useCase.FindStationsByNameUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class StationQuery(
    val findStationByIdUseCase: FindStationByIdUseCase,
    val findStationsByNameUseCase: FindStationsByNameUseCase,
    val stationMapper: StationMapper
) : FindStationByIdQueryResolver, SearchStationQueryResolver {

    @QueryMapping
    override fun findStationById(@Argument stationId: String): StationOutput? {
        return findStationByIdUseCase.findStationById(stationId)?.let {
            stationMapper.toStationOutput(it)
        }
    }

    @QueryMapping
    override fun searchStation(@Argument query: String): List<StationOutput>? {
        return findStationsByNameUseCase.findStationsByName(query).map {
            stationMapper.toStationOutput(it!!)
        }
    }
}
