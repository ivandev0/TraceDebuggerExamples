package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that iteration over mapped sequence and continuing in it works.
 *
 * Expected result:
 * Trace contains loop with 5 iterations: the last one has the check for `iterator.hasNext()` which returns false.
 * Starting with the 2nd iteration assignment to `escape = "n is saved"` is missing. Each iteration already has doubled values of i.
 */
class SequenceContinuedForAndMapLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val seq = sequenceOf(1, 2, 3, 4)
        for (i in seq.map { it * 2 }) {
            val a: Any = i
            escape = a.toString()
            if (i > 2) {
                continue
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
