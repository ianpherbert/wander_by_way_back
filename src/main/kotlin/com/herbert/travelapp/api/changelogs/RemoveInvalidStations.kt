package com.herbert.travelapp.api.changelogs

import com.herbert.travelapp.api.dataprovider.database.city.CityDB
import com.herbert.travelapp.api.dataprovider.database.station.StationDB
import io.github.oshai.KLogger
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

@ChangeUnit(id = "removeInvalidStations_01", order = "001", runAlways = true, author = "Ian Patrick Herbert")
class RemoveInvalidStations(
    val mongoTemplate: MongoTemplate,
    val logger: KLogger
) {

    @Execution
    fun removeInvalidStations() {
        val stations = Query().addCriteria(Criteria.where("apiId").`is`("INVALID")).let {
            mongoTemplate.find(it, StationDB::class.java, "stationDB")
        }.map { it.id }
        val cities = Query().addCriteria(Criteria.where("trainStations.stationId").`in`(stations)).let {
            mongoTemplate.find(it, CityDB::class.java, "cityDB")
        }
        cities.forEach { city ->
            val old = city.trainStations?.size!!
            val saveCity = city.apply {
                this.trainStations = city.trainStations?.filter {
                    !stations.contains(it.stationId)
                }
            }
            mongoTemplate.save(saveCity,"cityDB").let {
                val changed = old - saveCity.trainStations?.size!!
                logger.info("${city.name} - $changed stations removed")
            }
        }
    }

    @RollbackExecution
    fun rollback() {
    }
}
