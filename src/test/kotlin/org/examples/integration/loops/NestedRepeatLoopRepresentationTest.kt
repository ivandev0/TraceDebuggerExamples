package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that inner repeat loop is properly detected.
 *
 * Expected result:
 * Trace contains loop with 2 iterations, and an inner repeat loop with 4 iterations each.
 */
class NestedRepeatLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (j in 1..2) {
            repeat(5) { i ->
                val a: Any = i
                escape = a.toString()
                if (i > 3) {
                    return@repeat
                }
                escape = "${a} is saved"
            }
        }
        escape = "END"
    }
}
