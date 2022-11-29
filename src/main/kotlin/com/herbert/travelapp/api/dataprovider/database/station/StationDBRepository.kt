package com.herbert.travelapp.api.dataprovider.database.station

import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.connector.StationFindAllByApiIdIn
import com.herbert.travelapp.api.core.station.connector.StationFindAllByIdIn
import com.herbert.travelapp.api.core.station.connector.StationFindAllByName
import com.herbert.travelapp.api.core.station.connector.StationFindAllBySlugContaining
import com.herbert.travelapp.api.core.station.connector.StationFindByApiId
import com.herbert.travelapp.api.core.station.connector.StationFindById
import com.herbert.travelapp.api.core.station.connector.StationUpdate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface StationDBRepository : MongoRepository<StationDB, String> {
    fun findAllByName(name: String): List<StationDB>

    fun findByApiId(apiId: String): StationDB?

    fun findAllByIdIn(ids: List<String>): List<StationDB>

    fun findAllByApiIdIn(apiIds: List<String>): List<StationDB>

    fun findAllBySlugContaining(fragment: String): List<StationDB>
}

@Repository
class StationDBService(
    val stationDBRepository: StationDBRepository,
    val stationDBMapper: StationDBMapper
) : StationFindAllByApiIdIn,
    StationFindAllByIdIn,
    StationFindAllByName,
    StationFindAllBySlugContaining,
    StationFindByApiId,
    StationFindById,
    StationUpdate {
    override fun updateStation(station: Station): Station {
        return stationDBMapper.toStationDb(station).let {
            stationDBRepository.save(it)
        }.let {
            println("${station.name} updated")
            stationDBMapper.toStation(it)
        }
    }

    override fun findStationById(id: String): Station? {
        return stationDBRepository.findById(id).orElse(null)?.let {
            stationDBMapper.toStation(it)
        }
    }

    override fun findStationByApiId(apiId: String): Station? {
        return stationDBRepository.findByApiId(apiId)?.let {
            stationDBMapper.toStation(it)
        }
    }

    override fun findStationsByName(name: String): List<Station> {
        return stationDBRepository.findAllByName(name).map {
            stationDBMapper.toStation(it)
        } ?: listOf()
    }
    override fun findAllStationsByIdIn(ids: List<String>): List<Station> {
        return stationDBRepository.findAllByIdIn(ids).map {
            stationDBMapper.toStation(it)
        }
    }

    override fun findAllByApiIdIn(apiIds: List<String>): List<Station> {
        return stationDBRepository.findAllByApiIdIn(apiIds).map {
            stationDBMapper.toStation(it)
        }
    }

    override fun findAllBySlugContaining(fragment: String): List<Station> {
        return stationDBRepository.findAllBySlugContaining(fragment).map {
            stationDBMapper.toStation(it)
        }
    }
}
