package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that method calls are properly nested inside loop.
 *
 * Expected result:
 * Trace contains 2 loop iteration, with method call in each of them.
 */
class ForLoopWithMethodCallRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..2) {
            val a: Any = i
            method(a)
        }
        escape = "END"
    }

    private fun method(a: Any) {
        escape = a.toString()
    }
}
