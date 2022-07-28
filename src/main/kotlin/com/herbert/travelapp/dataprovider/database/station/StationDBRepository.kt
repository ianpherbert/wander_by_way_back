package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.core.station.StationRepository
import com.herbert.travelapp.dataprovider.database.city.CityDB
import com.herbert.travelapp.dataprovider.database.station.StationDB
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface StationDBRepository : MongoRepository<StationDB, String> {
}

@Component
class StationDBService(

) : StationRepository{

}