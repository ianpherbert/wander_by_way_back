package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.core.airport.AirportRepository
import com.herbert.travelapp.api.dataprovider.database.city.CityDBRepository
import com.herbert.travelapp.api.dataprovider.database.station.StationDBRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Transactional
interface AirportDBRepository : MongoRepository<AirportDB, String> {
    fun findByName(name: String) : AirportDB?
}

@Repository
class AirportDBService(
    val airportDBRepository: AirportDBRepository,
    val cityDBRepository: CityDBRepository,
    val stationDBRepository: StationDBRepository
) : AirportRepository {

}