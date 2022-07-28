package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.core.airport.AirportRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface AirportDBRepository : MongoRepository<AirportDB, String> {
}

@Component
class AirportDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository
) : AirportRepository{

}