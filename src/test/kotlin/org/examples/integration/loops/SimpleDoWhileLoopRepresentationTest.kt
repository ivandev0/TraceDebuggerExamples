package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that `do { } while` loop is detected`.
 *
 * Expected result:
 * Trace contains a `do { ... } while` loop with 2 iterations.
 */
class SimpleDoWhileLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        var i = 2
        do {
            val a: Any = i
            escape = a.toString()
            i++
        } while (i < 4)
        escape = "END"
    }
}
