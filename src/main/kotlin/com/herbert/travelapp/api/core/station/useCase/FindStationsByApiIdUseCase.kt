package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface FindStationsByApiIdUseCase {
    fun findStationByApiId(apiId: String): Station?
}
