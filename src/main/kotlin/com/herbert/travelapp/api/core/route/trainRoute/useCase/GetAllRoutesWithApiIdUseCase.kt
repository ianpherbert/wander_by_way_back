package com.herbert.travelapp.api.core.route.trainRoute.useCase

import com.herbert.travelapp.api.core.route.Route

/**
 * This function takes an api id and finds the corresponding API ID
 */
interface GetAllRoutesWithApiIdUseCase {
    /**
     * This function takes an existing Station object and finds the corresponding API ID
     *  @property apiId the id that will be used to search for its routes
     *  @return List of routes that have the id as origin
     */
    fun getAllRoutesWithApiId(apiId: String): List<Route>
}
