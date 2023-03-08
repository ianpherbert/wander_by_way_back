package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
import com.herbert.travelapp.api.dataprovider.database.common.routeDB.RouteDB
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class StationDB {

    @Id
    var id: String? = null

    var companyIds: List<DBCompanyId>? = null

    var apiId: String? = null

    var type: StationDBType? = null

    var name: String? = null

    var slug: String? = null

    var uicId: String? = null

    var latitude: Double? = null

    var longitude: Double? = null

    var country: String? = null

    var main: Boolean? = null

    var airport: Boolean? = null

    var airportId: String? = null

    var routes: List<RouteDB> = listOf()

    var matchCheck: Boolean = false
}

class DBCompanyId {
    var company: String? = null

    var companyId: String? = null
}

enum class StationDBType {
    TRAIN,
    BUS,
    FERRY,
    OTHER
}
