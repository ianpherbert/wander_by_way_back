package com.herbert.travelapp.api.entrypoint.graphql.city

import com.herbert.graphql.model.CityOutput
import com.herbert.graphql.model.CitySearchOutput
import com.herbert.travelapp.api.core.city.City
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CityMapper {
    fun toCityOutput(city: City) : CityOutput

    fun toCitySearchOutput(city: City) : CitySearchOutput
}