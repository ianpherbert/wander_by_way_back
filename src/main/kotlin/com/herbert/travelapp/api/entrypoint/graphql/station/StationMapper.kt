package com.herbert.travelapp.api.entrypoint.graphql.station

import com.herbert.graphql.model.StationOutput
import com.herbert.travelapp.api.core.station.Station
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StationMapper {
    fun toStationOutput(station: Station): StationOutput
}
