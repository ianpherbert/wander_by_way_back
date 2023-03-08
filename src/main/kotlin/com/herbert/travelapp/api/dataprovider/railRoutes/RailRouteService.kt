package com.herbert.travelapp.api.dataprovider.railRoutes

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.herbert.travelapp.api.core.route.trainRoute.TrainRoute
import com.herbert.travelapp.api.core.route.trainRoute.TrainRouteRepository
import com.herbert.travelapp.api.core.station.Station
import com.herbert.travelapp.api.core.station.StationType
import com.herbert.travelapp.api.core.station.connector.RouteFindStationInformation
import com.herbert.travelapp.api.core.station.connector.RouteStationApiIdFind
import com.herbert.travelapp.api.extensions.toSearchableName
import com.herbert.travelapp.api.utils.DistanceCalculator
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI

@Component
class RailRouteService(
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper,
    @Value("\${direkt-bahn.RouteUrl}")
    val routeUrl: String,
    @Value("\${direkt-bahn.SearchUrl}")
    val searchUrl: String
) : TrainRouteRepository, RouteStationApiIdFind, RouteFindStationInformation {
    override fun findRoutesFromStation(fromStation: Station): List<TrainRoute> {
        val url = URI("$routeUrl/${fromStation.apiId}")
        return try {
            restTemplate.exchange(url, HttpMethod.GET, HttpEntity(null, null), List::class.java).let {
                objectMapper.readValue(objectMapper.writeValueAsString(it.body), List::class.java).map {
                    objectMapper.readValue(objectMapper.writeValueAsString(it), RailRoute::class.java).let { response ->
                        TrainRoute().apply {
                            this.fromStationName = fromStation.name
                            this.fromStationId = fromStation.apiId
                            this.toStationName = response.name
                            this.toStationId = response.id
                            this.latitude = response.location?.latitude
                            this.longitude = response.location?.longitude
                            this.duration = response.duration
                            this.durationHours = response.duration?.div(60)
                            this.durationMinutes = response.duration?.rem(60)
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            println(ex.message)
            listOf()
        }
    }

    override fun findStationId(station: Station): String? {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        val url = URI("$searchUrl?query=${station.slug}")
        val request = HttpEntity(null, HttpHeaders())
        val resultList = restTemplate.exchange(url, HttpMethod.GET, request, List::class.java).body?.map {
            objectMapper.readValue(objectMapper.writeValueAsString(it), RailStopSearchResult::class.java)
        }

        return resultList?.filter { it.name?.toSearchableName() == station.name.toSearchableName() }?.takeIf { it.isNotEmpty() }.let {
            if (it?.size == 1) {
                it.first()
            } else {
                it?.find { result ->
                    DistanceCalculator(station.toPoint(), result.toPoint()).distance('K').let {
                        it > 0 && it < 5
                    }
                }
            }
        }?.id ?: resultList?.find { result ->
            DistanceCalculator(station.toPoint(), result.toPoint()).distance('K').let {
                it > 0 && it < 5
            }
        }?.id
    }

    override fun findStationInformation(apiId: String): Station? {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        val url = URI("$searchUrl?query=$apiId")
        val request = HttpEntity(null, HttpHeaders())
        val result = restTemplate.exchange(url, HttpMethod.GET, request, List::class.java).body?.map {
            objectMapper.readValue(objectMapper.writeValueAsString(it), RailStopSearchResult::class.java)
        }?.first()

        if (result?.name.isNullOrBlank() ||
            result?.location?.latitude == null ||
            result.location?.longitude == null
        ) return null

        return result.let {
            Station(
                name = it.name!!,
                slug = it.name!!.toSearchableName(),
                apiId = it.id,
                type = StationType.TRAIN,
                latitude = it.location?.latitude!!.toDouble(),
                longitude = it.location?.longitude!!.toDouble()

            )
        }
    }
}
