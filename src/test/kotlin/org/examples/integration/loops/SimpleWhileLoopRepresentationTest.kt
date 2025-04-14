package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that `while` loop is detected`.
 *
 * Expected result:
 * Trace contains a `while` loop with 3 iterations.
 */
class SimpleWhileLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        var i = 1
        while (i < 4) {
            val a: Any = i
            escape = a.toString()
            i++
        }
        escape = "END"
    }
}
