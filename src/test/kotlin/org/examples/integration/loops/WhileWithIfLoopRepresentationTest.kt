package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that `while` loop is properly detected
 *
 * Expected result:
 * Trace contains 11 iterations, with 2 trace points in each third one,
 * and 1 trace point in all others.
 */
class WhileWithIfLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        var i = 0
        var total = 0
        while (true) {
            total++
            if (total > 10) {
                break
            }
            val a: Any = i
            escape = a.toString()
            i = (i + 1) % 3
            if (i == 0) {
                escape = "%3-" + escape
            }
        }
        escape = "END"
    }
}
