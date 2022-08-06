package com.herbert.travelapp.api.dataprovider.flight


import com.fasterxml.jackson.databind.ObjectMapper
import com.herbert.travelapp.api.core.flight.Flight
import com.herbert.travelapp.api.core.flight.FlightLocation
import com.herbert.travelapp.api.core.flight.FlightRepository
import com.herbert.travelapp.api.core.flight.FlightStop
import java.net.URI
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class FlightSearchService(
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper,
    @Value("\${tequila.apiKey}")
    val tequilaApiKey: String
) : FlightRepository {
    override fun findFlights(from: String, to: String?, fromDate: String, toDate: String?): List<Flight>? {
        val paramMap = HashMap<String, String>().apply {
            set("fly_from", from)
            to?.let {
                set("fly_to", it)
            }
            set("dateFrom", fromDate)
            toDate?.let {
                set("dateTo", it)
            }
        }


        return try{
            callApi<FlightSearchResult>(
                buildUrlWithParams(TequilaURL.SEARCH_FLIGHT, paramMap),
                HttpMethod.GET,
            ).let {
                it?.data?.map {
                    mapToFlight(it)
                } ?: listOf()
            }
        }catch (ex: Exception){
            null
        }
    }

    private inline fun <reified T> callApi(url: URI, method: HttpMethod): T? {
        val request = HttpEntity(null, buildHeaders())
        return restTemplate.exchange(url, method, request, Map::class.java).let {
            if (it.statusCode.is2xxSuccessful) {
                objectMapper.readValue(objectMapper.writeValueAsString(it.body), T::class.java)
            } else {
                null
            }
        }
    }

    private fun buildHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            set("apiKey", tequilaApiKey)
        }
    }

    private fun buildUrlWithParams(url: TequilaURL, params: Map<String, String>): URI {
        val paramString = params.map {
            "${it.key.toString()}=${it.value.toString()}"
        }.joinToString("&")
        return URI("${url.value}?$paramString")
    }

    private fun mapToFlight(flight: FlightSearchResultData): Flight {
        return Flight().apply {
            this.kiwiId = flight.id!!
            this.from = FlightLocation().apply {
                this.name = flight.cityFrom!!
                this.airportCode = flight.cityCodeFrom
                this.countryName = flight.countryFrom?.name
                this.countryCode = flight.countryFrom?.code
            }
            this.to = FlightLocation().apply {
                this.name = flight.cityTo!!
                this.airportCode = flight.cityCodeTo
                this.countryName = flight.countryTo!!.name
                this.countryCode = flight.countryTo.code
            }
            this.distance = flight.distance
            this.price = flight.price
            this.localArrival = flight.local_arrival
            this.localDeparture = flight.local_departure
            this.utcArrival = flight.utc_arrival
            this.utcDeparture = flight.utc_departure
            this.duration = flight.duration?.get("departure")?.toInt()?.div(60)
            this.route = flight.route?.map { stop ->
                FlightStop().apply {
                    this.id = stop.id
                    this.flyFrom = stop.flyFrom
                    this.flyTo = stop.flyTo
                    this.cityFrom = stop.cityFrom
                    this.cityTo = stop.cityTo
                    this.cityCodeFrom = stop.cityCodeFrom
                    this.cityCodeTo = stop.cityCodeTo
                    this.bagRecheck = stop.bags_recheck_required
                    this.airline = stop.airline
                    this.localArrival = stop.local_arrival
                    this.localDeparture = stop.local_departure
                    this.utcArrival = stop.utc_arrival
                    this.utcDeparture = stop.utc_departure
                }
            }
        }
    }

}
