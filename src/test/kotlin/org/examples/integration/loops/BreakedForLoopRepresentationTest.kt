package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that non-full iteration (ended with `break`) is detected as part of the loop
 *
 * Expected result:
 * Trace contains loop with 3 iterations, first two with 2 trace points (`escape` assignment) each
 * and last one with only 1 trace point
 */
class BreakedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..5) {
            val a: Any = i
            escape = a.toString()
            if (i > 3) {
                break
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
