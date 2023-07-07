package com.herbert.travelapp.api.entrypoint.graphql.airport

import com.herbert.graphql.model.AirportOutput
import com.herbert.travelapp.api.core.airport.Airport
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AirportMapper {
    fun toAirportOutput(airport: Airport): AirportOutput
}
