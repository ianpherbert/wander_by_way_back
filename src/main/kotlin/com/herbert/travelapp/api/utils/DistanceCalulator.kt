package com.herbert.travelapp.api.utils

import kotlin.math.cos
import kotlin.math.sin

class DistanceCalculator(
    val pointA: Point,
    val pointB: Point
) {
    fun distance(unit: Char): Double {
        val theta = pointA.longitude - pointB.longitude
        var dist =
            Math.sin(deg2rad(pointA.latitude)) * Math.sin(deg2rad(pointB.latitude)) + Math.cos(deg2rad(pointA.latitude)) * Math.cos(deg2rad(pointB.latitude)) * Math.cos(
                deg2rad(theta)
            )
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        if (unit == 'K') {
            dist = dist * 1.609344
        } else if (unit == 'N') {
            dist = dist * 0.8684
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
)