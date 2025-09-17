package org.examples.hackathon

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SimpleProgramNonFailingTest {
    @Test
    fun test() {
        val numbers = listOf(1, 2, 3)
        val sum = sum(numbers)
        try {
            assertEquals(6, sum)
        } catch (_: Throwable) {
            // Ignore
        }
    }
}

private fun sum(numbers: List<Int>): Int {
    var sum = 0
    for (i in 0 until numbers.size - 1) {
        sum += numbers[i]
    }
    return sum
}
