package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.core.airport.Airport
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AirportDBMapper {
    fun toAirport(airportDB: AirportDB) : Airport
}