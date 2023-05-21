package com.herbert.travelapp.api.dataprovider.database.city

import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.connector.*
import com.herbert.travelapp.api.extensions.toSearchableName
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface CityDBRepository : MongoRepository<CityDB, String> {
    fun findByName(name: String): List<CityDB>

    fun findAllBySlugContaining(name: String): List<CityDB>

    fun findByShareId(shareId: String): CityDB?

    fun findAllByAreaIdContaining(areaId: String): List<CityDB>

    fun findAllByTrainStationsStationId(stationId: String): List<CityDB>

    fun findAllByTrainStationsApiId(stationId: String): List<CityDB>

    fun findAllByTrainStationsName(name: String): List<CityDB>

    fun findAllByAirportsAirportId(stationId: String): List<CityDB>

    fun findAllByAirportsIata(iataCode: String): List<CityDB>
}

@Repository
class CityDBService(
    val cityDBRepository: CityDBRepository,
    val cityDBMapper: CityDBMapper
) : CityGetById,
    CitySearchByName,
    CityGetByShareId,
    CityGetByAreaId,
    CityGetByStationId,
    CityGetAllByStationName,
    CityGetAllByStationApiId,
    CityGetByAirportId,
    CitySave,
    CityGetAllByIataCodeOrAirportId {
    override fun getCityById(id: String): City? {
        return cityDBRepository.findById(id).orElse(null)?.let { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun searchCitiesByName(name: String): List<City> {
        return cityDBRepository.findAllBySlugContaining(name.toSearchableName()).map { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCityByShareId(shareId: String): City? {
        return cityDBRepository.findByShareId(shareId)?.let { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCitiesByAreaId(areaId: String): List<City> {
        return cityDBRepository.findAllByAreaIdContaining(areaId).map {
            cityDBMapper.toCity(it)
        } ?: emptyList()
    }

    override fun findCitiesByStationId(stationId: String): List<City> {
        return cityDBRepository.findAllByTrainStationsStationId(stationId).map { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCitiesByAirportId(airportId: String): List<City> {
        return cityDBRepository.findAllByAirportsAirportId(airportId).map { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun saveCity(city: City): City {
        return cityDBMapper.toCityDB(city).let {
            cityDBRepository.save(it)
        }.let {
            cityDBMapper.toCity(it)
        }
    }

    override fun findAllByStationApiId(apiId: String): List<City> {
        return cityDBRepository.findAllByTrainStationsApiId(apiId).let {
            cityDBMapper.toCities(it)
        }
    }

    override fun findAllByStationName(name: String): List<City> {
        return cityDBRepository.findAllByTrainStationsName(name).let {
            cityDBMapper.toCities(it)
        }
    }

    override fun getByAirportIdOrIata(identifier: String): List<City> {
        val cities = if(identifier.length.equals(3)){
            cityDBRepository.findAllByAirportsIata(identifier)
        }else{
            cityDBRepository.findAllByAirportsAirportId(identifier)
        }
        return cityDBMapper.toCities(cities)
    }
}
