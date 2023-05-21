package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.dataprovider.database.common.routeDB.RouteDB
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AirportDBMapper {
    fun toAirport(airportDB: AirportDB): Airport

    fun toAirportDB(airport: Airport): AirportDB

    fun toRouteDB(route: Route): RouteDB
}
