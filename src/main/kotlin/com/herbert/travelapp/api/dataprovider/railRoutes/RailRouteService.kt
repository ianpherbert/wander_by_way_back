package com.herbert.travelapp.api.dataprovider.railRoutes

import com.fasterxml.jackson.databind.ObjectMapper
import com.herbert.travelapp.api.core.trainRoute.TrainRoute
import com.herbert.travelapp.api.core.trainRoute.TrainRouteRepository
import java.net.URI
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RailRouteService(
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper,
    @Value("\${direkt-bahn.RouteUrl}")
    val routeUrl: String,
    @Value("\${direkt-bahn.SearchUrl}")
    val searchUrl: String
) : TrainRouteRepository {
    override fun findRoutesFromStation(stationId: String): List<TrainRoute>? {
        return getStation(stationId)?.let { fromStation ->
            val url = URI("$routeUrl/$stationId")
            try{
                restTemplate.exchange(url, HttpMethod.GET, HttpEntity(null, null), List::class.java).let {
                    objectMapper.readValue(objectMapper.writeValueAsString(it.body), List::class.java).map {
                        objectMapper.readValue(objectMapper.writeValueAsString(it), RailRoute::class.java).let{response ->
                            TrainRoute().apply {
                                this.fromStationName = fromStation.name
                                this.fromStationId = fromStation.id
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
            }catch (ex: Exception){
                println(ex.message)
                null
            }
        }
    }

    private fun getStation(stationId: String): RailLocation? {
        val url = URI("$searchUrl?query=$stationId")
        val request = HttpEntity(null, HttpHeaders())
        return restTemplate.exchange(url, HttpMethod.GET, request, List::class.java).let {
            objectMapper.readValue(objectMapper.writeValueAsString(it.body), List::class.java).first().let {
                objectMapper.readValue(objectMapper.writeValueAsString(it), RailLocation::class.java)
            }
        }
    }
}