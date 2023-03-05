package com.herbert.travelapp.api.core.route.trainRoute.useCase

import com.herbert.travelapp.api.core.route.Route

interface GetAllRoutesFromAPIIdUseCase {
    fun getAllRoutesFromAPIId(apiId: String): List<Route>
}
