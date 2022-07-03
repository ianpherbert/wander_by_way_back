package com.herbert.travelapp.dataprovider.flight


import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date
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
) : FlightSearcher{
    override fun findFlight(searchParams: FlightSearchParams) : FlightSearchResult?{
        val paramMap = searchParams.let {
            HashMap<String, String>().apply {
                set("fly_from", it.from)
                it.to?.let {
                    set("fly_to", it)
                }
                set("dateFrom", it.fromDate)
                it.toDate?.let {
                    set("dateTo", it)
                }
            }
        }

        return callApi<FlightSearchResult>(buildUrlWithParams(TequilaURL.SEARCH_FLIGHT,paramMap), HttpMethod.GET, null)
    }

    private inline fun <reified T>  callApi(url: URI, method: HttpMethod, params: FlightRequestDto?) : T?{
        val request = HttpEntity(params,buildHeaders())
        return restTemplate.exchange(url,method, request, Map::class.java).let{
            if(it.statusCode.is2xxSuccessful){
                objectMapper.readValue(objectMapper.writeValueAsString(it.body), T::class.java)
            }else{
                null
            }
        }
    }

    private fun buildHeaders(): HttpHeaders{
        return HttpHeaders().apply {
            set("apiKey", tequilaApiKey)
        }
    }

    private fun buildUrlWithParams(url: TequilaURL, params: Map<String, String>) : URI {
        val paramString = params.map{
                "${it.key.toString()}=${it.value.toString()}"
            }.joinToString("&")
        return URI("${url.value}?$paramString")
    }


}

class FlightSearchParams(
    val from: String,
    val to: String?,
    val fromDate: String,
    val toDate: String?
)