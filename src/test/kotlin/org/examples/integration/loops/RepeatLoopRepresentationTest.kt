package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that non-full simple usage of repeat loop is detected.
 *
 * Expected result:
 * Trace contains loop with 4 iterations, with i going from 0 to 4 excluding 4.
 */
class RepeatLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        repeat(5) { i ->
            val a: Any = i
            escape = a.toString()
            if (i > 3) {
                return@repeat
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
