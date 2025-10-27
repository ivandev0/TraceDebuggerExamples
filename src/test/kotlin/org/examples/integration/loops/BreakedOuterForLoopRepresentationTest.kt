package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that break of the outer loop from inner loop works.
 *
 * Expected result:
 * Trace contains the loop over k with 3 iterations, each containing a single iteration of the middle loop,
 * which contains the loops over i with 2 iterations.
 */
class BreakedOuterForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (k in 0..2) {
            middle@ for (j in 1..3) {
                for (i in 1..5) {
                    val a: Any = i
                    escape = a.toString()
                    if (i % 2 == 0) {
                        break@middle
                    }
                    escape = "${a} is odd"
                }
            }
        }
        escape = "END"
    }
}
