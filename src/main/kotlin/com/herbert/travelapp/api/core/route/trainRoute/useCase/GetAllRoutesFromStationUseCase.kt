package com.herbert.travelapp.api.core.route.trainRoute.useCase

import com.herbert.travelapp.api.core.route.Route
import com.herbert.travelapp.api.core.station.Station

/**
 * Used to get routes from station
 */
interface GetAllRoutesFromStationUseCase {
    /**
     * This function takes an existing Station object and returns the routes that have this station as an origin
     *  @property fromStation the station that will be used to search for its routes
     *  @return the routes that are found from the station
     */
    fun getAllRoutesFromStation(fromStation: Station): List<Route>
}
