package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.utils.DistanceCalculator
import com.herbert.travelapp.api.utils.Point

class Route {
    var to: RouteStop? = null
    var from: RouteStop? = null
    var type: RouteType? = null
    var durationTotal: Int? = null
    var durationMinutes: Int? = null
    var durationHours: Int? = null
    var lineDistance: Double? = null
        get() = DistanceCalculator(to?.toPoint()!!, from?.toPoint()!!).distance('K')
}

class RouteStop {
    var name: String? = null
    var id: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var country: String? = null
    fun toPoint(): Point {
        return Point(
            latitude!!.toDouble(),
            longitude!!.toDouble()
        )
    }
}

enum class RouteType {
    TRAIN,
    BUS,
    PLANE,
    BOAT,
    CAR,
    OTHER
}
