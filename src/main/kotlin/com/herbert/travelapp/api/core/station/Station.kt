package com.herbert.travelapp.api.core.station

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.route.RouteStop
import com.herbert.travelapp.api.dataprovider.database.city.CityTypeDB
import com.herbert.travelapp.api.utils.Point

class Station {

    var id: String? = null

    var companyIds: List<CompanyId>? = null

    var apiId: String? = null

    var type: StationType? = null

    var name: String? = null

    var slug: String? = null

    var uicId: String? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var main: Boolean? = null

    var airport: Boolean? = null

    var parentId: String? = null

    var routes: List<Route> = listOf()
        get() = field.map { route ->
            route.apply {
                this.from = toRouteStop()
            }
        }

    fun toPoint(): Point {
        return Point(
            latitude!!.toDouble(),
            longitude!!.toDouble()
        )
    }

    private fun toRouteStop(): RouteStop {
        val station = this
        return RouteStop().apply {
            this.name = station.name
            this.id = station.id
            this.latitude = station.latitude
            this.longitude = station.longitude
            this.country = station.country
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

class StationCity {
    var cityId: String? = null

    var name: String? = null

    var slug: String? = null

    var type: CityTypeDB? = null

    var latitude: String? = null

    var longitude: String? = null

    var country: String? = null

    var shareId: String? = null

    fun toPoint(): Point {
        return Point(
            latitude!!.toDouble(),
            longitude!!.toDouble()
        )
    }
}
