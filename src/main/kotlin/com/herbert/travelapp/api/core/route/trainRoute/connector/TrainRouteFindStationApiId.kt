package com.herbert.travelapp.api.core.route.trainRoute.connector

import com.herbert.travelapp.api.core.station.Station

/**
 * Connector used to find the API ID for a station.
 */
interface TrainRouteFindStationApiId {
    /**
     * This function takes an existing Station object and finds the corresponding API ID
     *  @property station the station that will be used to search for its corresponding ID
     *  @return The ID found for the station, or undefined
     */
    fun findStationId(station: Station): String?
}
