package com.herbert.travelapp.core.station

import org.springframework.stereotype.Component

@Component
class StationService(
    val stationRepository: StationRepository
) : StationProvider {
}