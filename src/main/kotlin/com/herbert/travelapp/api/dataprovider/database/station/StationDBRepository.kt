package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.api.core.station.StationRepository
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBRepository
import com.herbert.travelapp.api.dataprovider.database.station.StationDB
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface StationDBRepository : MongoRepository<StationDB, String> {
    fun findByName(name: String) : StationDB?
}

@Component
class StationDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository
) : StationRepository {

}