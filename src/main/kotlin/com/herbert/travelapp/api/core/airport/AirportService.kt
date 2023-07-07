package com.herbert.travelapp.api.core.airport

import com.herbert.travelapp.api.core.airport.connector.*
import com.herbert.travelapp.api.core.airport.useCase.*
import com.herbert.travelapp.api.core.route.Route
import org.springframework.stereotype.Component

@Component
class AirportService(
    val airportFindAllById: AirportFindAllById,
    val airportFindAllByName: AirportFindAllByName,
    val airportFindAllByIACOCode: AirportFindAllByIACOCode,
    val airportFindByIATACode: AirportFindByIATACode,
    val airportFindByICAOCode: AirportFindByICAOCode,
    val airportFindById: AirportFindById,
    val airportUpdateRoutes: AirportUpdateRoutes
) : FindAirportByIATACodeUseCase,
    FindAirportsByNameUseCase,
    FindAirportByICAOCodeUseCase,
    FindAirportByIdUseCase,
    FindAllAirportsByIdInUseCase,
    FindAirportsByIATACodeUseCase,
    UpdateAirportRoutesUseCase {
    override fun findAirportById(id: String): Airport? {
        return airportFindById.findAirportById(id)
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

    override fun updateRoutes(airportIATACode: String, routes: List<Route>): Airport {
       return airportUpdateRoutes.updateRoutes(airportIATACode, routes)
    }
}
