package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.dataprovider.database.common.routeDB.RouteDB
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class AirportDB {

    @Id
    var id: String? = null

    var location: AirportDBLocation? = null

    var name: String? = null

    var slug: String? = null

    var latitude: Double? = null

    var longitude: Double? = null

    var iata: String? = null

    var icao: String? = null

    var routes: List<RouteDB> = listOf()

    lateinit var updateDate: LocalDate
}

class AirportDBLocation {
    var country: String? = null

    var region: String? = null
}
