package com.herbert.travelapp.api.core.station.connector

import com.herbert.travelapp.api.core.station.Station

interface StationFindAllBySlugContaining {
    fun findAllBySlugContaining(fragment: String): List<Station>
}
