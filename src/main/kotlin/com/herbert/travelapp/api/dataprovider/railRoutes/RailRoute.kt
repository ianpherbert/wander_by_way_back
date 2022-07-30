package com.herbert.travelapp.api.dataprovider.railRoutes


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


class RailLocation{
    var name : String? = null
    var id : String? = null
}