package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that loop with single iteration is detected.
 *
 * Expected result:
 * Trace contains a loop with 1 iteration.
 */
class OneIterForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..1) {
            val a: Any = i
            escape = a.toString()
        }
        escape = "END"
    }
}
