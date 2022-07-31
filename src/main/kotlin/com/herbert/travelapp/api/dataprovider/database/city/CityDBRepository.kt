package com.herbert.travelapp.api.dataprovider.database.city

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.CityRepository
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBMapper
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBRepository
import com.herbert.travelapp.api.dataprovider.database.station.StationDBMapper
import com.herbert.travelapp.api.dataprovider.database.station.StationDBRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface CityDBRepository : MongoRepository<CityDB, String> {
    fun findByName(name: String) : List<CityDB>

    fun findAllByNameContaining(name: String) : List<CityDB>?
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
        return cityDBRepository.findById(id).orElse(null)?.let{ cityDB ->
            cityDBMapper.toCity(cityDB).let{ city ->
                parseCity(city,cityDB)
            }
        }
    }

    override fun getCitiesByName(name: String): List<City>? {
         return cityDBRepository.findAllByNameContaining(name)?.map{cityDB ->
            cityDBMapper.toCity(cityDB).let{
                parseCity(it, cityDB)
            }
        }
    }

    private fun parseCity(city: City, cityDB: CityDB) : City{
        return city.apply {
            this.airports = cityDB.airports?.map { cityAirportDB ->
                cityAirportDB.airportId?.let {
                    parseAirport(it)
                } ?: Airport().apply {
                    name = cityAirportDB.name
                }
            }
            this.trainStations = cityDB.trainStations?.map { cityStationDB ->
                cityStationDB.stationId?.let {
                    parseStation(it)
                } ?: Station().apply {
                    name = cityStationDB.name
                }
            }
        }
    }

    private fun parseAirport(airportId: String) : Airport? {
        return airportDBRepository.findById(airportId).orElse(null)?.let {
            airportDBMapper.toAirport(it)
        }
    }

    private fun parseStation(stationId: String) : Station?{
        return stationDBRepository.findById(stationId).orElse(null)?.let {
            stationDBMapper.toStation(it)
        }
    }

}