package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that empty `for` loop is not detected.
 *
 * Expected result:
 * Trace doesn't contain loops, as nothing was executed inside loop.
 */
class ZeroIterForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val start = 2
        val end = 1
        for (i in start..end) {
            val a: Any = i
            escape = a.toString()
        }
        escape = "END"
    }
}
