package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that break of the outer loop from repeat works.
 *
 * Expected result:
 * Trace contains outer loop with 1 iteration, and the inner repeat contains 3 iterations
 */
class BreakedNestedRepeatLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        loop@ for (j in 1..2) {
            repeat(5) { i ->
                val a: Any = i
                escape = a.toString()
                if (i == 2) {
                    break@loop
                }
                escape = "${a} is saved"
                println(escape)
            }
        }
        escape = "END"
    }
}
