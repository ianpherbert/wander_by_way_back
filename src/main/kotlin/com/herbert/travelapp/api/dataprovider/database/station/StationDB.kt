package com.herbert.travelapp.api.dataprovider.database.station

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

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var main: Boolean? = null

    var airport: Boolean? = null

    var parentId: String? = null
}

class DBCompanyId{
    var company: String? = null

    var companyId: String? = null
}

enum class StationDBType{
    TRAIN,
    BUS,
    FERRY,
    OTHER
}