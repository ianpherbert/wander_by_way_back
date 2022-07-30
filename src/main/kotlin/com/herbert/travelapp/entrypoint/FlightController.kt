package com.herbert.travelapp.entrypoint

import com.herbert.travelapp.core.city.CityProvider
import com.herbert.travelapp.dataprovider.longTransport.CityTravelData
import com.herbert.travelapp.dataprovider.longTransport.RouteFinder
import com.herbert.travelapp.dataprovider.database.airport.AirportDB
import com.herbert.travelapp.dataprovider.database.airport.AirportDBRepository
import com.herbert.travelapp.dataprovider.database.airport.StationDBRepository
import com.herbert.travelapp.dataprovider.database.station.StationDB
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/flights/routes")
class FlightController(
    val routeFinder: RouteFinder,
    val cityProvider: CityProvider
) {

    @GetMapping("/all")
    fun getAllDirectFlights(
        @RequestParam(name = "from", required = true) from: String,
        @RequestParam(name = "fromDate", required = false) fromDate: String?
    ) : CityTravelData? {
        return routeFinder.findAllRoutesFromAirport(from, fromDate ?: formatDate(LocalDate.now()), fromDate?.let { oneDayFromString(it) } ?: formatDate(LocalDate.now().plusDays(1)))
    }

    @GetMapping("/")
    fun getFlightsBetweenCities(
        @RequestParam(name = "to", required = false) to: String,
        @RequestParam(name = "toDate", required = false) toDate: String?,
        @RequestParam(name = "from", required = true) from: String,
        @RequestParam(name = "fromDate", required = false) fromDate: String?
    ): CityTravelData? {
        return routeFinder.findRoutesBetweenCities(from,to,fromDate ?: formatDate(LocalDate.now()),toDate ?: fromDate ?: formatDate(LocalDate.now().plusDays(1)))
    }

    @GetMapping("/test")
    fun testSaveData() : String{
        cityProvider.findCityById("62d02351a28bff57433701e5")
        return "ok"
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
    }

    private fun oneDayFromString(date: String) : String{
        return formatDate(date.split("/").map{it.toInt()}.let{
            LocalDate.of(it[2],it[1],it[0]).plusDays(1)
        })
    }
}