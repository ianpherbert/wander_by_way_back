package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.Airport
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AirportDBMapper {
    fun toAirport(airportDB: AirportDB): Airport
}
