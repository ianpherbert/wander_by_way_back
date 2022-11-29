package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface FindStationsByNameUseCase {
    fun findStationsByName(name: String): List<Station?>
}
