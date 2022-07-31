package com.herbert.travelapp.api.entrypoint.graphql.airport

import com.herbert.graphql.model.AirportOutput
import com.herbert.graphql.model.AirportSearchOutput
import com.herbert.graphql.model.FindAirportQueryResolver
import com.herbert.graphql.model.SearchAirportQueryResolver
import com.herbert.travelapp.api.core.airport.AirportProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller

@Controller
class AirportQuery(
    val airportProvider: AirportProvider
) : SearchAirportQueryResolver, FindAirportQueryResolver {
    override fun findAirport(@Argument airportId: String): AirportOutput? {
        TODO("Not yet implemented")
    }

    override fun searchAirport(@Argument query: String?): MutableList<AirportSearchOutput?> {
        TODO("Not yet implemented")
    }
}