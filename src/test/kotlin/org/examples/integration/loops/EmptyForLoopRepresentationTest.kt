package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that loop without events is not detected
 *
 * Expected result:
 * No loop presents in the trace.
 */
class EmptyForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val builder = StringBuilder()
        for (i in 1..2) {
            builder.append(i)
        }
        escape = builder.toString()
        escape = "END"
    }
}
