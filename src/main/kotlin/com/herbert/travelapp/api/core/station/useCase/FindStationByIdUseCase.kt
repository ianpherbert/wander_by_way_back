package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface FindStationByIdUseCase {
    fun findStationById(id: String): Station?
}
