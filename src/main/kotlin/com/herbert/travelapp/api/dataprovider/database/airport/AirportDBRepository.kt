package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.AirportRepository
import com.herbert.travelapp.dataprovider.database.airport.CityDBRepository
import com.herbert.travelapp.dataprovider.database.airport.StationDBRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface AirportDBRepository : MongoRepository<AirportDB, String> {
    fun findByName(name: String) : AirportDB?
}

@Component
class AirportDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository
) : AirportRepository {

}