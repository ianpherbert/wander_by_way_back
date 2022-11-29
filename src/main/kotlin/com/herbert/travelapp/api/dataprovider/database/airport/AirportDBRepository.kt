package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.Airport
import com.herbert.travelapp.api.core.airport.connector.AirportFindAllByIACOCode
import com.herbert.travelapp.api.core.airport.connector.AirportFindAllById
import com.herbert.travelapp.api.core.airport.connector.AirportFindAllByName
import com.herbert.travelapp.api.core.airport.connector.AirportFindByIATACode
import com.herbert.travelapp.api.core.airport.connector.AirportFindByICAOCode
import com.herbert.travelapp.api.core.airport.connector.AirportFindByIdUseCase
import com.herbert.travelapp.api.extensions.toSearchableName
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface AirportDBRepository : MongoRepository<AirportDB, String> {

    fun findAllBySlugContaining(name: String): List<AirportDB>?

    fun findByIata(iata: String): AirportDB?

    fun findByIcao(icao: String): AirportDB?

    fun findAllByIataIn(icaoCodes: List<String>): List<AirportDB>?

    fun findAllByIdIn(ids: List<String>): List<AirportDB>
}

@Repository
class AirportDBServiceUseCase(
    val airportDBRepository: AirportDBRepository,
    val airportDBMapper: AirportDBMapper
) : AirportFindAllById,
    AirportFindAllByName,
    AirportFindAllByIACOCode,
    AirportFindByIATACode,
    AirportFindByICAOCode,
    AirportFindByIdUseCase {
    override fun findAirportById(id: String): Airport? {
        return airportDBRepository.findById(id).orElse(null)?.let {
            airportDBMapper.toAirport(it)
        }
    }

    override fun findAirportsByName(name: String): List<Airport>? {
        return airportDBRepository.findAllBySlugContaining(name.toSearchableName())?.map {
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

    override fun findAirportsByIACOCode(codes: List<String>): List<Airport>? {
        return airportDBRepository.findAllByIataIn(codes)?.map {
            airportDBMapper.toAirport(it)
        }
    }

    override fun findAllAirportsByIdIn(ids: List<String>): List<Airport> {
        return airportDBRepository.findAllByIdIn(ids).map {
            airportDBMapper.toAirport(it)
        }
    }
}
