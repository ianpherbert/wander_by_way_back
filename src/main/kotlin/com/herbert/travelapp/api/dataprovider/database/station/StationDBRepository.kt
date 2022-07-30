package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.core.station.StationRepository
import com.herbert.travelapp.api.dataprovider.database.airport.AirportDBRepository
import com.herbert.travelapp.api.dataprovider.database.city.CityDBRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface StationDBRepository : MongoRepository<StationDB, String> {
    fun findByName(name: String) : StationDB?
}

@Repository
class StationDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository
) : StationRepository {

}