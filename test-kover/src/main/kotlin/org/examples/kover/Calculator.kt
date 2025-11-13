package org.examples.kover

/**
 * A tiny calculator used to demonstrate Kover coverage in a dedicated module.
 */
class Calculator {
    fun add(a: Int, b: Int): Int = a + b

    fun subtract(a: Int, b: Int): Int = a - b

    fun isEven(n: Int): Boolean = n % 2 == 0
}
