package com.herbert.travelapp.api.core.route

import com.herbert.travelapp.api.utils.DistanceCalculator
import com.herbert.travelapp.api.utils.Point

class Route(
    var to: RouteStop,
    var type: RouteType,
    var durationTotal: Int,
    var durationMinutes: Int,
    var durationHours: Int
) {
    lateinit var from: RouteStop
    val lineDistance: Double
        get() = DistanceCalculator(to.toPoint(), from.toPoint()).distance('K')
}

class RouteStop(
    var name: String,
    var id: String,
    var latitude: Double,
    var longitude: Double,
    var country: String? = null,
    var iata: String? = null
) {
    fun toPoint(): Point {
        return Point(
            latitude,
            longitude
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
