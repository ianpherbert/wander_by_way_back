package com.herbert.travelapp.core.longTransport

import com.herbert.travelapp.dataprovider.flight.FlightSearchParams
import com.herbert.travelapp.dataprovider.flight.FlightSearchResult
import com.herbert.travelapp.dataprovider.flight.FlightSearchResultData
import com.herbert.travelapp.dataprovider.flight.FlightSearcher
import org.springframework.stereotype.Component

@Component
class TravelService(
    val flightSearcher: FlightSearcher
) : RouteFinder {

    override fun findAllRoutesFromAirport(airportCode: String, fromDate: String, toDate: String): CityTravelData? {
        return flightSearcher.findFlight(FlightSearchParams(airportCode, null, fromDate, toDate))?.let { result ->
            mapToCityTravelData(result.data!!)
        }
    }

    override fun findRoutesBetweenCities(
        fromAirportCode: String,
        toAirportCode: String,
        fromDate: String,
        toDate: String
    ): CityTravelData? {
        return flightSearcher.findFlight(FlightSearchParams(fromAirportCode, toAirportCode, fromDate, toDate))?.let { result ->
            mapToCityTravelData(result.data!!)
        }
    }

    private fun mapToCityTravelData(data: List<FlightSearchResultData?>) : CityTravelData{
        return CityTravelData().apply {
            this.cityFrom = data!!.first()!!.cityFrom!!
            this.results = data.size
            this.minStops = data.minOf { it!!.route!!.size }
            this.countries = data.groupBy {
                it?.countryTo?.code
            }.map {
                CountryFlights().apply {
                    this.name = it.value.first()!!.countryTo!!.name
                    this.code = it.key!!
                    this.results = it.value.size
                    this.flightsTo = it.value.map { flight ->
                        Flight().apply {
                            this.kiwiId = flight!!.id!!
                            this.from = Location().apply {
                                this.name = flight.cityFrom!!
                                this.airportCode = flight.cityFrom
                                this.countryName = flight.countryFrom!!.name
                                this.countryCode = flight.countryFrom.code
                            }
                            this.to = Location().apply {
                                this.name = flight.cityTo!!
                                this.airportCode = flight.cityTo
                                this.countryName = flight.countryTo!!.name
                                this.countryCode = flight.countryTo.code
                            }
                            this.distance = flight.distance
                            //this.duration = flight.duration. Get key!!!
                            this.price = flight.price
                            this.localArrival = flight.local_arrival!!
                            this.localDeparture = flight.local_departure!!
                            this.utcArrival = flight.utc_arrival!!
                            this.utcDeparture = flight.utc_departure!!
                            this.route = flight.route?.map { stop ->
                                FlightStop().apply {
                                    this.id = stop.id!!
                                    this.flyFrom = stop.flyFrom!!
                                    this.flyTo = stop.flyTo!!
                                    this.cityFrom = stop.cityFrom!!
                                    this.cityTo = stop.cityTo!!
                                    this.cityCodeFrom = stop.cityCodeFrom!!
                                    this.cityCodeTo = stop.cityCodeTo!!
                                    this.bagRecheck = stop.bags_recheck_required
                                    this.airline = stop.airline!!
                                    this.localArrival = stop.local_arrival!!
                                    this.localDeparture = stop.local_departure!!
                                    this.utcArrival = stop.utc_arrival!!
                                    this.utcDeparture = stop.utc_departure!!
                                }
                            }
                        }
                    }
                }
            }
        }

}

}