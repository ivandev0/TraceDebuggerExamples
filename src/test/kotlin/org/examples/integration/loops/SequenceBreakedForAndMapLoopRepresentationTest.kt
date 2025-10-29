package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that iteration over mapped sequence and breaking from it works.
 *
 * Expected result:
 * Trace contains 2 iterations in total, the second one does not have "n is saved" assignment to `escape` variable.
 */
class SequenceBreakedForAndMapLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val seq = sequenceOf(1, 2, 3, 4)
        for (i in seq.map { it * 2 }) {
            val a: Any = i
            escape = a.toString()
            if (i > 2) {
                break
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
