package com.herbert.travelapp.dataprovider.database.airport

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class AirportDB {

    @Id
    var id: String? = null

    var location: AirportDBLocation? = null

    var name: String? = null

    var latitude: String? = null

    var longitude: String? = null

    var iata: String? = null

    var icao: String? = null
}

class AirportDBLocation{
    var country: String? = null

    var region: String? = null

    var cities: List<String>? = null

}