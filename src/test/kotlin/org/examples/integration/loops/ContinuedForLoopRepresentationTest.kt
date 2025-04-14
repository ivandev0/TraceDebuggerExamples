package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that non-full iterations (ended with `continue`) are detected as part of the loop
 *
 * Expected result:
 * Trace contains loop with 5 iterations, even ones with 1 trace point each and odd ones
 * with 2 trace points each.
 */
class ContinuedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..5) {
            val a: Any = i
            escape = a.toString()
            if (i % 2 == 0) {
                continue
            }
            escape = "${a} is odd"
        }
        escape = "END"
    }
}
