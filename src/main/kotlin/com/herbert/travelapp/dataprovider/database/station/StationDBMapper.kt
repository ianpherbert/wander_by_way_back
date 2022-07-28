package com.herbert.travelapp.dataprovider.database.station

import com.herbert.travelapp.core.station.Station
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface StationDBMapper {
    fun toStation(stationDB: StationDB) : Station
}