package org.examples.kover

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CalculatorTest {
    private val calc = Calculator()

    @Test
    fun addTest() {
        assertEquals(7, calc.add(3, 4))
        assertEquals(-1, calc.subtract(3, 4))
    }

    @Test
    fun isEvenTest() {
        assertTrue(calc.isEven(2))
        assertFalse(calc.isEven(3))
    }
}
