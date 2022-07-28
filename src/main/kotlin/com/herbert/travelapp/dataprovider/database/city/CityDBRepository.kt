package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.core.city.City
import com.herbert.travelapp.core.city.CityRepository
import com.herbert.travelapp.dataprovider.database.city.CityDB
import com.herbert.travelapp.dataprovider.database.city.CityDBMapper
import com.herbert.travelapp.dataprovider.database.station.StationDBMapper
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface CityDBRepository : MongoRepository<CityDB, String> {
}

@Component
class CityDBService(
    val airportDBRepository: AirportDBRepository,
    val airportDBMapper: AirportDBMapper,
    val cityDBRepository: CityDBRepository,
    val cityDBMapper: CityDBMapper,
    val stationDBRepository: StationDBRepository,
    val stationDBMapper: StationDBMapper
) : CityRepository{
    override fun getCityById(id: String): City? {
        return cityDBRepository.findById(id).orElse(null)?.let{
            cityDBMapper.toCity(it)
        }
    }

}