package com.herbert.travelapp.api.core.route

class RouteSearchItem(
    val id: String,
    val type: PointType,
    val filters: RoutSearchFilter
)

class RoutSearchFilter(
    val flight: Boolean,
    val train: Boolean,
    val bus: Boolean,
    val ferry: Boolean
)

enum class PointType {
    CITY,
    AIRPORT,
    PORT,
    STATION,
    OTHER,
    POINT,
}
