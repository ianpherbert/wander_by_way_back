package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.core.station.Station
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
    val stationDBRepository: StationDBRepository,
    val stationDBMapper: StationDBMapper,
) : StationRepository {
    override fun updateStation(station: Station): Station {
        return stationDBMapper.toStationDb(station).let{
            stationDBRepository.save(it)
        }.let{
            println("${station.name} apiId updated")
            stationDBMapper.toStation(it)
        }
    }

}