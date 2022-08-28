package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB


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

    var cityList: List<StationCity>? = null
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

class StationCity{
    var cityId: String? = null

    var name: String? = null

    var slug: String? = null

    var type: CityTypeDB? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null
}