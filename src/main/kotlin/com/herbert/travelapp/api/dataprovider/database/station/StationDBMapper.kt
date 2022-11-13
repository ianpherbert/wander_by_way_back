package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.dataprovider.database.common.routeDB.RouteDB
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StationDBMapper {

    fun toStation(stationDB: StationDB): Station

    fun toStationDb(station: Station): StationDB

    fun toRoute(routeDB: RouteDB): Route
}
