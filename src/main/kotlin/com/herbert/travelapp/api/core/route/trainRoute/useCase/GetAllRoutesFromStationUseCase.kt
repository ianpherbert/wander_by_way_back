package com.herbert.travelapp.api.core.route.trainRoute.useCase

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.station.Station

interface GetAllRoutesFromStationUseCase {

    fun getAllRoutesFromStation(fromStation: Station): List<Route>
}
