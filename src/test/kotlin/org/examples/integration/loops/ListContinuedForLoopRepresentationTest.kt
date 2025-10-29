package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that for loop over collections work.
 *
 * Expected result:
 * Trace contains loop with 5 iterations, the 4th one does not have "n is saved" opposingly to the rest, and the 5th one is just the check for `iterator.hasNext()`.
 */
class ListContinuedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val list = listOf(1, 2, 3, 4)
        for (i in list) {
            val a: Any = i
            escape = a.toString()
            if (i > 3) {
                continue
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
