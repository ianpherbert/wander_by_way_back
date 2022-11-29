package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface UpdateStationApiIdUseCase {
    fun updateStationApiId(station: Station): Station
}
