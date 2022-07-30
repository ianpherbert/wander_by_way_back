package com.herbert.travelapp.core.station

import org.springframework.data.annotation.Id

class Station {

    var id: String? = null

    var companyIds: List<CompanyId>? = null

    var apiId: String? = null

    var type: StationType? = null

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

class CompanyId{
    var company: String? = null

    var companyId: String? = null
}

enum class StationType{
    TRAIN,
    BUS,
    FERRY,
    OTHER
}