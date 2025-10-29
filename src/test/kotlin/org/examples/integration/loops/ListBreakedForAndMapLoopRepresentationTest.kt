package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that iteration over mapped list and breaking from it works.
 *
 * Expected result:
 * Trace contains 5 iterations of mapping: the last one has the check for `iterator.hasNext()` which returns false.
 * Then 2 iterations of the for loop which is then breaked
 */
class ListBreakedForAndMapLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        val list = listOf(1, 2, 3, 4)
        for (i in list.map { it * 2 }) {
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
