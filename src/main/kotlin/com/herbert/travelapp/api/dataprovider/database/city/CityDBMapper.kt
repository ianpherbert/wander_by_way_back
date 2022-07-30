package com.herbert.travelapp.api.dataprovider.database.city

import com.herbert.travelapp.api.core.city.City
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CityDBMapper {

    fun toCity(cityDB: CityDB) : City
}