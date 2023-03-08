package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
import com.herbert.travelapp.api.utils.Point

class Station(


    var type: StationType,

    var name: String,

    var slug: String,

    var latitude: Double ? = null,

    var longitude: Double? = null,

    var airport: Boolean = false,

    var airportId: String? = null,

    var apiId: String? = null,

    var uicId: String? = null,

    var companyIds: List<CompanyId>? = listOf()
) {
    var country: String? = null

    var id: String? = null
    var routes: List<Route> = listOf()
        get() = field.map { route ->
            route.apply {
                this.from = toRouteStop()
            }
        }

    fun toPoint(): Point {
        return Point(
            latitude ?: 0.00,
            longitude ?: 0.00
        )
    }

    private fun toRouteStop(): RouteStop {
        return RouteStop(
            name = this.name,
            id = this.id ?: "",
            latitude = this.latitude ?: 0.00,
            longitude = this.longitude ?: 0.00,
            country = this.country
        )
    }

    companion object {
        fun dummyStation(apiId: String): Station {
            return Station(
                StationType.TRAIN,
                "dummy",
                "dummy",
                0.00,
                0.00,
                false,
                "dummy",
                "dummy",
                apiId,
                listOf()
            )
        }
    }
}

class CompanyId {
    var company: String? = null

    var companyId: String? = null
}

enum class StationType {
    TRAIN,
    BUS,
    FERRY,
    OTHER
}

