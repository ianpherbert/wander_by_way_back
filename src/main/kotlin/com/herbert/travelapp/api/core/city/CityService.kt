package com.herbert.travelapp.api.core.city

import com.herbert.travelapp.api.core.city.connector.CityGetAllByStationApiId
import com.herbert.travelapp.api.core.city.connector.CityGetAllByStationName
import com.herbert.travelapp.api.core.city.connector.CityGetByAirportId
import com.herbert.travelapp.api.core.city.connector.CityGetByAreaId
import com.herbert.travelapp.api.core.city.connector.CityGetById
import com.herbert.travelapp.api.core.city.connector.CityGetByShareId
import com.herbert.travelapp.api.core.city.connector.CitySearchByName
import com.herbert.travelapp.api.core.city.useCase.FindByCityIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindByShareIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAirportIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByApiIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByAreaIdUseCase
import com.herbert.travelapp.api.core.city.useCase.FindCitiesByNameUseCase
import org.springframework.stereotype.Component

@Component
class CityService(
    val cityGetById: CityGetById,
    val citySearchByName: CitySearchByName,
    val cityGetByShareId: CityGetByShareId,
    val cityGetByAreaId: CityGetByAreaId,
    val cityGetAllByStationName: CityGetAllByStationName,
    val cityGetAllByStationApiId: CityGetAllByStationApiId,
    val cityGetByAirportId: CityGetByAirportId
) : FindByCityIdUseCase,
    FindByShareIdUseCase,
    FindCitiesByAirportIdUseCase,
    FindCitiesByApiIdUseCase,
    FindCitiesByAreaIdUseCase,
    FindCitiesByNameUseCase {
    override fun findCityById(id: String): City? {
        return cityGetById.getCityById(id)
    }

    override fun findCitiesByName(name: String): List<City> {
        return citySearchByName.searchCitiesByName(name)
    }

    override fun findCityByShareId(shareId: String): City? {
        return cityGetByShareId.findCityByShareId(shareId)
    }

    override fun findCitiesByAreaId(areaId: String): List<City> {
        return cityGetByAreaId.findCitiesByAreaId(areaId)
    }

    override fun findCitiesByApiId(stationApiId: String, name: String): List<City> {
        val byApiId = cityGetAllByStationApiId.findAllByStationApiId(stationApiId)
        return byApiId.ifEmpty {
            cityGetAllByStationName.findAllByStationName(name)
        }
    }

    override fun findCitiesByAirportId(airportId: String): List<City> {
        return cityGetByAirportId.findCitiesByAirportId(airportId)
    }
}
