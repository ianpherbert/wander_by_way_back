package com.herbert.travelapp.dataprovider.database.airport

import org.springframework.data.mongodb.repository.MongoRepository

interface AirportDBRepository : MongoRepository<AirportDB, String> {
}