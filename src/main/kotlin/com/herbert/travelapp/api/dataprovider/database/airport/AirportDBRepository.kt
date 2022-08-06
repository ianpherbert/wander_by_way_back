package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.airport.AirportRepository
import com.herbert.travelapp.api.dataprovider.database.city.CityDBRepository
import com.herbert.travelapp.api.dataprovider.database.station.StationDBRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Transactional
interface AirportDBRepository : MongoRepository<AirportDB, String> {
    fun findAllByName(name: String) : List<AirportDB>?

    fun findByIata(iata: String) : AirportDB?

    fun findByIcao(icao: String) : AirportDB?
}

@Repository
class AirportDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository,
    val airportDBMapper: AirportDBMapper
) : AirportRepository {
    override fun findAirportById(id: String): Airport? {
        return airportDBRepository.findById(id).orElse(null)?.let {
            airportDBMapper.toAirport(it)
        }
    }

    override fun findAirportsByName(name: String): List<Airport>? {
        return airportDBRepository.findAllByName(name)?.map {
            airportDBMapper.toAirport(it)
        }
    }

    override fun findAirportByIATACode(iata: String): Airport? {
        return airportDBRepository.findByIata(iata)?.let {
            airportDBMapper.toAirport(it)
        }
    }

    override fun findAirportByICAOCode(icao: String): Airport? {
        return airportDBRepository.findByIcao(icao)?.let {
            airportDBMapper.toAirport(it)
        }
    }

}