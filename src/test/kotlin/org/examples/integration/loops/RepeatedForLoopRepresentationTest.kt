package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that each instance of syntactically same loop is detected and instances are not "glued" together.
 *
 * Expected result:
 * Trace contains two distinct loops, each with 2 iterations and different tracepoints inside.
 */
class RepeatedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        loop("Loop-A")
        escape = "MID"
        loop("Loop-B")
        escape = "END"
    }

    private fun loop(prefix: String) {
        escape = "${prefix}-START"
        for (i in 1..2) {
            val a: Any = i
            escape = "${prefix}-${a}"
        }
        escape = "${prefix}-END"
    }
}
