package com.herbert.travelapp.api.core.route.trainRoute.connector

import com.herbert.travelapp.api.core.station.Station

/**
 * Connector used to find station information
 */
interface TrainRouteFindStationInformation {
    /**
     * This function takes an apiId and returns a new Station object
     *  @property apiId the id that will be used to search for its station information
     *  @return The station that is found with the id provided, or undefined
     */
    fun findStationInformation(apiId: String): Station?
}
