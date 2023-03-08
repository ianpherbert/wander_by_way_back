package com.herbert.travelapp.api.changelogs

import com.herbert.travelapp.api.dataprovider.database.airport.AirportDB
import com.herbert.travelapp.api.dataprovider.database.city.CityDB
import com.herbert.travelapp.api.dataprovider.database.city.CityStationDB
import com.herbert.travelapp.api.dataprovider.database.station.StationDB
import com.herbert.travelapp.api.dataprovider.database.station.StationDBType
import com.herbert.travelapp.api.extensions.toSearchableName
import com.herbert.travelapp.api.utils.Perimeter
import com.herbert.travelapp.api.utils.Point
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import java.lang.Exception

@ChangeUnit(id = "matchEntities", order = "002", author = "Ian Patrick Herbert", runAlways = false)
class MatchDBChangelog(
    val mongoTemplate: MongoTemplate
) {
    val stationThreshold = 10.00

    @Execution
    fun doMatches() {
        matchStations()
        cleanDeadStationsFromCity()
    }

    fun cleanDeadStationsFromCity() {
        val cities = mongoTemplate.find(
            Query().addCriteria(Criteria.where("trainStations.0").exists(true)),
            CityDB::class.java,
            "cityDB"
        )
        var sum = 0
        val avg = ArrayList<Int>()

        for (city in cities) {
            val realStations = city.trainStations?.map { ObjectId(it.stationId) }?.flatMap { stationIds ->
                val query = Query().addCriteria(Criteria.where("_id").`is`(stationIds))
                mongoTemplate.find(query, StationDB::class.java, "stationDB").map {
                    it.id
                }
            } ?: listOf()
            if (city.trainStations?.size != realStations.size) {
                val changeNum = city.trainStations?.size?.minus(realStations.size)
                avg.add(changeNum ?: 0)
                println("${city.name}: $changeNum")
                mongoTemplate.save(
                    city.apply {
                        this.trainStations = this.trainStations?.filter { realStations.contains(it.stationId) }
                    },
                    "cityDB"
                )
            }
        }
        for (num in avg) {
            sum += num
        }
        println("${sum / avg.size} average deleted")
    }

    fun matchStations() {
        // Retrieve all stations that do not yet have a matchCheck
        // Avoid retrieving those entities without latitude to avoid errors
        val stations = Query().addCriteria(Criteria.where("matchCheck").isEqualTo(false).and("latitude").exists(true)).let {
            mongoTemplate.find(it, StationDB::class.java, "stationDB")
        }
        for (station in stations) {
            try {
                matchStationsToCities(station)
            } catch (e: Exception) {
                println("error updating ${station.name} city Check")
            }
            try {
                matchStationsToAirports(station)
            } catch (e: Exception) {
                println("error updating ${station.name} airport check")
            }
            mongoTemplate.save(
                station.apply {
                    matchCheck = true
                },
                "stationDB"
            )
        }
    }

    private fun matchStationsToAirports(station: StationDB) {
        val perimeter: Perimeter = if (station.airport == true) {
            Point(
                station.latitude!!.toDouble(),
                station.longitude!!.toDouble()
            ).calculateSquarePerimeter(10.00, 'K')
        } else {
            Point(
                station.latitude!!.toDouble(),
                station.longitude!!.toDouble()
            ).calculateSquarePerimeter(1.00, 'K')
        }

        val criteria: List<Criteria> = buildPerimeterCriteria(perimeter)
        val airports = Query().addCriteria(Criteria().andOperator(criteria)).let {
            mongoTemplate.find(it, AirportDB::class.java, "airportDB")
        }
        if (airports.size >= 1) {
            mongoTemplate.save(
                station.apply {
                    this.airport = true
                    this.airportId = airports.first().id
                },
                "stationDB"
            )
        }
    }

    private fun matchStationsToCities(station: StationDB) {
        val perimeter: Perimeter = Point(
            station.latitude!!.toDouble(),
            station.longitude!!.toDouble()
        ).calculateSquarePerimeter(stationThreshold, 'K')
        val criteria: List<Criteria> = buildPerimeterCriteria(perimeter)
        val cities = Query().addCriteria(Criteria().andOperator(criteria)).let {
            mongoTemplate.find(it, CityDB::class.java, "cityDB")
        }

        val matchedCities = cities.filter { station.name?.toSearchableName()?.contains(it.name!!.toSearchableName()) == true }

        for (city in matchedCities) {
            if (city.trainStations?.mapNotNull { it.stationId }?.contains(station.id) == false) {
                city.apply {
                    val cityStation = CityStationDB().apply {
                        this.name = station.name
                        this.stationId = station.id
                        this.type = StationDBType.TRAIN
                        this.latitude = station.latitude
                        this.longitude = station.longitude
                        this.apiId = station.apiId
                        this.main = false
                        this.airport = false
                    }
                    this.trainStations = this.trainStations?.plus(cityStation)
                }.let {
                    mongoTemplate.save(it, "cityDB")
                }
            }
        }
    }
    
    private fun buildPerimeterCriteria(perimeter: Perimeter): List<Criteria> {
        return ArrayList<Criteria>().apply {
            this.add(Criteria.where("latitude").gt(perimeter.south.latitude))
            this.add(Criteria.where("latitude").lt(perimeter.north.latitude))
            this.add(Criteria.where("longitude").gt(perimeter.west.longitude))
            this.add(Criteria.where("longitude").lt(perimeter.east.longitude))
        }
    }
    
    @RollbackExecution
    fun rollback() {
    }
}
