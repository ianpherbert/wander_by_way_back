package com.herbert.travelapp.api.core.station.useCase

import com.herbert.travelapp.api.core.station.Station

interface FindAllByApiIdUseCase {
    fun findAllByApiIdIn(apiIds: List<String>): List<Station>
}
