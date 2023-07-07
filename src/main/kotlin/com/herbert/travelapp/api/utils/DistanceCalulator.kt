package com.herbert.travelapp.api.utils

import kotlin.math.absoluteValue
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class DistanceCalculator(
    private val pointA: Point,
    private val pointB: Point
) {
    fun distance(unit: Char): Double {
        val theta = pointA.longitude - pointB.longitude
        var dist =
            sin(deg2rad(pointA.latitude)) * sin(deg2rad(pointB.latitude)) + cos(deg2rad(pointA.latitude)) * cos(deg2rad(pointB.latitude)) * cos(
                deg2rad(theta)
            )
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        if (unit == 'K') {
            dist *= 1.609344
        } else if (unit == 'N') {
            dist *= 0.8684
        }
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}

class Point(
    val latitude: Double,
    val longitude: Double
) {

    fun calculateSquarePerimeter(distance: Double, unit: Char): Perimeter {
        val distanceValue = when (unit) {
            'M' -> {
                milesToKilometers(distance)
            }
            'K' -> {
                distance
            }
            else -> {
                distance
            }
        }
        val latitudeValue = distanceValue.div(111)
        val longitudeValue = distanceValue.div(longitudeDistanceByLatitude(this.latitude, 'K'))
        return Perimeter(
            Point(this.latitude.plus(latitudeValue), this.longitude),
            Point(this.latitude.minus(latitudeValue), this.longitude),
            Point(this.latitude, this.longitude.plus(longitudeValue)),
            Point(this.latitude, this.longitude.minus(longitudeValue))
        )
    }
}

class Perimeter(
    val north: Point,
    val south: Point,
    val east: Point,
    val west: Point
)

fun kilometersToMiles(kilometers: Double): Double {
    return kilometers.times(0.621371)
}

fun milesToKilometers(miles: Double): Double {
    return miles.times(1.60934)
}

fun kilometersToMiles(kilometers: Int): Double {
    return kilometers.toDouble().times(0.621371)
}

fun milesToKilometers(miles: Int): Double {
    return miles.toDouble().times(1.60934)
}

fun longitudeDistanceByLatitude(latitude: Double, unit: Char): Int {
    return when (5 * ((latitude.absoluteValue / 5).roundToInt())) {
        0 -> 111
        5 -> 111
        10 -> 109
        15 -> 107
        20 -> 104
        25 -> 101
        30 -> 96
        35 -> 91
        40 -> 85
        45 -> 79
        50 -> 71
        55 -> 64
        60 -> 56
        65 -> 47
        70 -> 38
        75 -> 29
        80 -> 19
        85 -> 10
        90 -> 90
        else -> { throw Exception("Latitude is out of bounds") }
    }.let {
        if (unit == 'M') {
            milesToKilometers(it).toInt()
        } else {
            it
        }
    }
}
