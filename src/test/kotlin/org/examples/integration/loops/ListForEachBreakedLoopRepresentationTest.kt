package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that `forEach` loop over collection works and return from it as well.
 *
 * Expected result:
 * Trace contains loop with 3 iterations, first two with 2 trace points with `escape` assignment of "n is saved" each
 * and last one without it.
 */
class ListForEachBreakedLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val list = listOf(1, 2, 3, 4)
        list.forEach { i ->
            val a: Any = i
            escape = a.toString()
            if (i > 2) {
                return
            }
            escape = "${a} is saved"
        }
        escape = "END"
    }
}
