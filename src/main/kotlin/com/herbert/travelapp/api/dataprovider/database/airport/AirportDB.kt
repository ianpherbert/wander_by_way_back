package com.herbert.travelapp.api.dataprovider.database.airport

import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

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

    var matchCheck: Boolean = false
}

class AirportDBLocation {
    var country: String? = null

    var region: String? = null
}
