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
                city.apply {
                    this.airports = this.airports?.let {
                        parseAirports(it)
                    }
                    this.trainStations = this.trainStations?.let {
                        parseStations(it)
                    }
                }
            }
        }
    }

    private fun parseAirports(airports: List<Airport>) : List<Airport>{
        return airports.map{airport ->
            airport.name?.let { airportDBRepository.findByName(it) }?.let{
                airportDBMapper.toAirport(it)
            } ?: airport
        }
    }

    private fun parseStations(stations: List<Station>) : List<Station>{
        return stations.map {station ->
            station.name?.let { stationDBRepository.findByName(it) }?.let {
                stationDBMapper.toStation(it)
            } ?: station
        }
    }

}