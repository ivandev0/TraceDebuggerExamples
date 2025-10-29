package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that for loop over collections work.
 *
 * Expected result:
 * Trace contains loop with 4 iterations, the last one does not have "n is saved" opposingly to the rest.
 */
class ListBreakedForLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val list = listOf(1, 2, 3, 4)
        for (i in list) {
            val a: Any = i
            escape = a.toString()
            if (i > 3) {
                break
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
