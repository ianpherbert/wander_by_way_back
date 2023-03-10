package com.herbert.travelapp.api.core.route.trainRoute

import com.herbert.travelapp.api.utils.Point

class TrainRoute {
    var fromStationName: String? = null

    var fromStationId: String? = null

    var toStationName: String? = null

    var toStationId: String? = null

    var latitude: Double? = null

    var longitude: Double? = null

    var duration: Int? = null

    var durationHours: Int? = null

    var durationMinutes: Int? = null

    fun toPoint(): Point {
        return Point(
            latitude!!,
            longitude!!
        )
    }
}
