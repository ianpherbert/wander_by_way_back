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

    var latitude: String? = null

    var longitude: String? = null

    var iata: String? = null

    var icao: String? = null
}

class AirportDBLocation{
    var country: String? = null

    var region: String? = null

    var cityList: List<AirportCityDB>? = null
}

class AirportCityDB{
    var cityId: String? = null

    var name: String? = null

    var slug: String? = null

    var type: CityTypeDB? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null
}