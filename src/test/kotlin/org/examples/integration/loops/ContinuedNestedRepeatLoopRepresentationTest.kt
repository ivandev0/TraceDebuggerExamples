package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that continue of the outer loop from repeat works.
 *
 * Expected result:
 * Trace contains outer loop with 2 iteration, inside each iteration there is an inner repeat, each containing 3 iterations
 */
class ContinuedNestedRepeatLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        loop@ for (j in 1..2) {
            repeat(5) { i ->
                val a: Any = i
                escape = a.toString()
                if (i == 2) {
                    continue@loop
                }
                escape = "${a} is saved"
                println(escape)
            }
        }
        escape = "END"
    }
}
