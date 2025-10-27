package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that continuing the outer loop works.
 *
 * Expected result:
 * Trace contains loop with 3 iterations, each containing a loop over i with 2 iterations each.
 */
class ContinuedOuterForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        outer@ for (j in 1..3) {
            for (i in 1..5) {
                val a: Any = i
                escape = a.toString()
                if (i % 2 == 0) {
                    continue@outer
                }
                escape = "${a} is odd"
            }
        }
        escape = "END"
    }
}
