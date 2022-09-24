package com.herbert.travelapp.api

import com.herbert.travelapp.api.utils.DistanceCalculator
import com.herbert.travelapp.api.utils.Point
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.math.absoluteValue

@ExtendWith(SpringExtension::class)
class DistanceCalcTest {

    @Test
    fun testPerimeter() {
        val point = Point(47.1318, -1.3341)

        point.calculateSquarePerimeter(15.0, 'K').let {
            val distance = (DistanceCalculator(point, it.north).distance('K') - 15).absoluteValue
            assert(distance < 5)
        }
    }
}
