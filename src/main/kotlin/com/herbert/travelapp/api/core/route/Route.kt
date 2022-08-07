package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.core.city.CityType

class Route {
    var to: RouteStop? = null
    var from: RouteStop? = null
    var type: RouteType? = null
    var durationTotal: Int? = null
    var durationMinutes: Int? = null
    var durationHours: Int? = null
}

class RouteStop{
    var name: String? = null
    var id: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var country: String? = null
}

enum class RouteType{
    TRAIN,
    BUS,
    PLANE,
    BOAT,
    CAR,
    OTHER
}

class RailLocation(
    var name : String,
    var slug: String,
    var apiId : String?
)