package com.herbert.travelapp.dataprovider.database.city

import com.herbert.travelapp.core.city.City
import org.mapstruct.EnumMapping
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CityDBMapper {
    @EnumMapping(nameTransformationStrategy = "case", configuration = "UPPERCASE")
    fun toCity(cityDB: CityDB) : City
}