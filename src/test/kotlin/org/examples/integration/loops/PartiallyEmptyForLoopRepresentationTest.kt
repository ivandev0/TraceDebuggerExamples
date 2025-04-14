package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that when not every iteration has tracepoint, a loop is detected.
 *
 * Expected result:
 * Trace contains a loop with only 2 iterations.
 */
class PartiallyEmptyForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val builder = StringBuilder()
        for (i in 1..5) {
            builder.append(i)
            if (i % 2 == 0) {
                escape = builder.toString()
                builder.clear()
            }
        }
        escape = builder.toString()
        escape = "END"
    }
}
