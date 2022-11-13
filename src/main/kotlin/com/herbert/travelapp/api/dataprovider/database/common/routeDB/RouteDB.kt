package com.herbert.travelapp.api.dataprovider.database.common.routeDB

class RouteDB {
    var to: RouteDBStop? = null
    var type: RouteDBType? = null
    var durationTotal: Int? = null
    var durationMinutes: Int? = null
    var durationHours: Int? = null
}

class RouteDBStop {
    var name: String? = null
    var id: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var country: String? = null
}

enum class RouteDBType {
    TRAIN,
    BUS,
    PLANE,
    BOAT,
    CAR,
    OTHER
}
