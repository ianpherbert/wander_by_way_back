package com.herbert.travelapp.api.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class DistanceCalculator(
    private val pointA: Point,
    private val pointB: Point
) {
    fun distance(unit: Char): Double {
        val theta = pointA.longitude - pointB.longitude
        var dist =
            sin(pointA.latitudeRad()) * sin(pointB.latitudeRad()) + cos(pointA.latitudeRad()) * cos(pointB.latitudeRad()) * cos(
                deg2rad(theta)
            ).let {
                rad2deg(acos(it)) * 60 * 1.1515
            }
        if (unit == 'K') {
            dist *= 1.609344
        } else if (unit == 'N') {
            dist *= 0.8684
        }
        return dist
    }

    fun perimeter(point: Point, distance: Int, unit: Char): Perimeter {
        TODO("Calculate perimeter")
        //Lines of latitude are 111 km apart
        /* One degree of longitude is 111.321 km at the equator (0° latitude) and 0 km at the poles (90°) */
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
    fun latitudeRad(): Double {
        return latitude * Math.PI / 180.0
    }
}

class Perimeter(
    val north: Point,
    val south: Point,
    val east: Point,
    val west: Point
)