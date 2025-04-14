package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that `for` loop is detected`.
 *
 * Expected result:
 * Trace contains a `for` loop with 2 iterations.
 */
class SimpleForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..2) {
            val a: Any = i
            escape = a.toString()
        }
        escape = "END"
    }
}
