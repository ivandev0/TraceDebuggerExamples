package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that two different `for` loops are detected properly.
 *
 * Expected result:
 * Trace contains two loops with 5 iterations each.
 */
class TwoForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..5) {
            val a: Any = i
            escape = "A." + a.toString()
        }
        for (i in 1..5) {
            val a: Any = i
            escape = "B." + a.toString()
        }
        escape = "END"
    }
}
