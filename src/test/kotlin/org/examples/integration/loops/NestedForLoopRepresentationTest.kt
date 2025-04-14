package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that nested loops are properly detected and structured.
 *
 * Expected result:
 * Trace contains a loop with 2 iterations, and each iteration contains a loop with
 * 3 iterations.
 */
class NestedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..2) {
            val a: Any = i
            for (j in 1..3) {
                escape = "$a.$j"
            }
        }
        escape = "END"
    }
}
