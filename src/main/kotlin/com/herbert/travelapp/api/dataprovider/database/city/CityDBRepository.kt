package com.herbert.travelapp.api.dataprovider.database.city

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.CityRepository
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBMapper
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBRepository
import com.herbert.travelapp.api.dataprovider.database.station.StationDBMapper
import com.herbert.travelapp.api.dataprovider.database.station.StationDBRepository
import com.herbert.travelapp.api.extensions.toSearchableName
import com.herbert.travelapp.api.extensions.unaccent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface CityDBRepository : MongoRepository<CityDB, String> {
    fun findByName(name: String): List<CityDB>

    fun findAllBySlugContaining(name: String): List<CityDB>?

    fun findByShareId(shareId: String): CityDB?

    fun findAllByAreaIdContaining(areaId: String): List<CityDB>?

    fun findAllByTrainStationsStationId(stationId: String): List<CityDB>?

    fun findAllByAirportsAirportId(stationId: String): List<CityDB>?


}

@Repository
class CityDBService(
    val airportDBRepository: AirportDBRepository,
    val airportDBMapper: AirportDBMapper,
    val cityDBRepository: CityDBRepository,
    val cityDBMapper: CityDBMapper,
    val stationDBRepository: StationDBRepository,
    val stationDBMapper: StationDBMapper
) : CityRepository {
    override fun getCityById(id: String): City? {
        return cityDBRepository.findById(id).orElse(null)?.let { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun searchCitiesByName(name: String): List<City>? {
        return cityDBRepository.findAllBySlugContaining(name.toSearchableName())?.map { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCityByShareId(shareId: String): City? {
        return cityDBRepository.findByShareId(shareId)?.let { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCitiesByAreaId(areaId: String): List<String>? {
        return cityDBRepository.findAllByAreaIdContaining(areaId)?.map {
            it.id!!
        }
    }

    override fun findCitiesByStationId(stationId: String): List<City>? {
        return cityDBRepository.findAllByTrainStationsStationId(stationId)?.map { cityDB ->
            cityDBMapper.toCity(cityDB)
        }
    }

    override fun findCitiesByAirportId(airportId: String): List<City>? {
        return cityDBRepository.findAllByAirportsAirportId(airportId)?.map { cityDB ->
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


    private fun parseAirport(airportId: String): Airport? {
        return airportDBRepository.findById(airportId).orElse(null)?.let {
            airportDBMapper.toAirport(it)
        }
    }

    private fun parseStation(stationId: String): Station? {
        return stationDBRepository.findById(stationId).orElse(null)?.let {
            stationDBMapper.toStation(it)
        }
    }

}