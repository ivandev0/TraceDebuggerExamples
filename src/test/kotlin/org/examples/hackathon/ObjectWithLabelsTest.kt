package org.examples.hackathon

import org.junit.jupiter.api.Test

class ObjectWithLabelsTest {

    val b1 = Box(1)
    val b2 = Box(2)
    val b3 = Box(3)

    @Test
    fun test() {
        changeValues(b2, b3, b1)
        changeValues(b2, b3, b1)
    }

    private fun changeValues(
        b2: Box<Int>,
        b3: Box<Int>,
        b1: Box<Int>
    ) {
        b2.value = b3.value * 2
        b1.value = 6
    }

    data class Box<T>(var value: T)

}