package com.herbert.travelapp.api.dataprovider.railRoutes

import com.herbert.travelapp.api.utils.Point

class RailRoute {
    var id: String? = null
    var name: String? = null
    var location: RailRouteLocation? = null
    var duration: Int? = null
    var dbUrlGerman: String? = null
    var dbUrlEnglish: String? = null
    var calendarUrl: String? = null
}

class RailRouteLocation {
    var type: String? = null
    var id: String? = null
    var latitude: String? = null
    var longitude: String? = null
}

class RailStopSearchResult(
    var type: String?,
    var id: String?,
    var name: String?,
    var location: RailStopSearchResultLocation?
) {
    fun toPoint(): Point {
        return Point(
            location?.latitude ?: 0.00,
            location?.longitude ?: 0.00
        )
    }
}

class RailStopSearchResultLocation(
    var latitude: Double?,
    var longitude: Double?
)
