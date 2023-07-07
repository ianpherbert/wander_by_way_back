package com.herbert.travelapp.api.core.route.trainRoute.connector

import com.herbert.travelapp.api.core.route.trainRoute.TrainRoute
import com.herbert.travelapp.api.core.station.Station
/**
 * Connector used to find all available routes from station
 */
interface TrainRouteFindFromStation {

    fun findRoutes(fromStation: Station): List<TrainRoute>
}
