package com.herbert.travelapp.api.dataprovider.database.city

import com.herbert.travelapp.api.core.city.City
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CityDBMapper {

    fun toCity(cityDB: CityDB): City

    fun toCityDB(city: City): CityDB

    fun toCities(cityDBs: List<CityDB>): List<City>

    fun toCityDBs(cities: List<City>): List<CityDB>
}
