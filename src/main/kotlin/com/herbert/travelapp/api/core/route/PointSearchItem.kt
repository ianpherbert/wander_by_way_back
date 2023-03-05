package com.herbert.travelapp.api.core.route

class PointSearchItem(
    val id: String,
    val pointType: PointType
)

enum class PointType {
    CITY,
    AIRPORT,
    PORT,
    STATION,
    OTHER,
    POINT,
}
