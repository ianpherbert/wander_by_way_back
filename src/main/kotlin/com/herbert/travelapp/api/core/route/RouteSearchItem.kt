package com.herbert.travelapp.api.core.route

class RouteSearchItem(
    val id: String,
    val type: PointType
)

enum class PointType {
    CITY,
    AIRPORT,
    PORT,
    STATION,
    OTHER,
    POINT,
}
