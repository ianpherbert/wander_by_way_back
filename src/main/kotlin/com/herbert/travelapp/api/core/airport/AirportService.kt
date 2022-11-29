package com.herbert.travelapp.api.core.airport

import com.herbert.travelapp.api.core.airport.connector.AirportFindAllByIACOCode
import com.herbert.travelapp.api.core.airport.connector.AirportFindAllById
import com.herbert.travelapp.api.core.airport.connector.AirportFindAllByName
import com.herbert.travelapp.api.core.airport.connector.AirportFindByIATACode
import com.herbert.travelapp.api.core.airport.connector.AirportFindByICAOCode
import com.herbert.travelapp.api.core.airport.connector.AirportFindByIdUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportByIATACodeUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportByICAOCodeUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportByIdUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByIATACodeUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAirportsByNameUseCase
import com.herbert.travelapp.api.core.airport.useCase.FindAllAirportsByIdInUseCase
import org.springframework.stereotype.Component

@Component
class AirportService(
    val airportFindAllById: AirportFindAllById,
    val airportFindAllByName: AirportFindAllByName,
    val airportFindAllByIACOCode: AirportFindAllByIACOCode,
    val airportFindByIATACode: AirportFindByIATACode,
    val airportFindByICAOCode: AirportFindByICAOCode,
    val airportFindByIdUseCase: AirportFindByIdUseCase
) : FindAirportByIATACodeUseCase,
    FindAirportsByNameUseCase,
    FindAirportByICAOCodeUseCase,
    FindAirportByIdUseCase,
    FindAllAirportsByIdInUseCase,
    FindAirportsByIATACodeUseCase {
    override fun findAirportById(id: String): Airport? {
        return airportFindByIdUseCase.findAirportById(id)
    }

    override fun findAirportsByName(name: String): List<Airport> {
        return airportFindAllByName.findAirportsByName(name)
    }

    override fun findAirportByIATACode(iata: String): Airport? {
        return airportFindByIATACode.findAirportByIATACode(iata)
    }

    override fun findAirportByICAOCode(icao: String): Airport? {
        return airportFindByICAOCode.findAirportByICAOCode(icao)
    }

    override fun findAirportsByIATACode(iataCodes: List<String>): List<Airport> {
        return airportFindAllByIACOCode.findAirportsByIACOCode(iataCodes)
    }

    override fun findAllAirportsByIdIn(ids: List<String>): List<Airport> {
        return airportFindAllById.findAllAirportsByIdIn(ids)
    }
}
