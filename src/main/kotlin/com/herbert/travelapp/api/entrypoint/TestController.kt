package com.herbert.travelapp.api.entrypoint

import com.herbert.travelapp.api.core.city.City
import com.herbert.travelapp.api.core.city.CityProvider
import com.herbert.travelapp.api.core.trainRoute.TrainRoute
import com.herbert.travelapp.api.core.trainRoute.TrainRouteProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    val trainRouteProvider: TrainRouteProvider,
    val cityProvider: CityProvider
) {
    @GetMapping("/city/{cityId}")
    fun getCity(@PathVariable cityId: String) : City? {
        return cityProvider.findCityById(cityId)
    }

    @GetMapping("/train/{stationId}")
    fun getTrainRouteData(@PathVariable stationId: String) : List<TrainRoute>?{
        return trainRouteProvider.getAllRoutesFromStation(stationId)
    }
}