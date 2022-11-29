package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface FindAllStationsByIdUseCase {
    fun findAllStationsByIdIn(ids: List<String>): List<Station>
}
