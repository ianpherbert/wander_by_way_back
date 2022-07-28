package com.herbert.travelapp.dataprovider.database.airport

import com.herbert.travelapp.core.city.CityRepository
import com.herbert.travelapp.dataprovider.database.city.CityDB
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface CityDBRepository : MongoRepository<CityDB, String> {
}

@Component
class CityDBService(

) : CityRepository{

}