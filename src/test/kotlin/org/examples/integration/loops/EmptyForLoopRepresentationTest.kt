package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that loop without events is not detected
 *
 * Expected result:
 * This loop doesn't contain any events which \[now\] lincheck reports as trace points
 * (it could be changed in the future with support for local variables).
 * Plugin detects loops only by looking of placement of rseported trace points.
 * As there are no trace points inside the loop, the plugin does not detect the loop.
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
