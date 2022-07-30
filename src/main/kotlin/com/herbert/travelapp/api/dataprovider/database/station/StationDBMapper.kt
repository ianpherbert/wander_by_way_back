package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.core.station.Station
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StationDBMapper {
    fun toStation(stationDB: StationDB) : Station
}