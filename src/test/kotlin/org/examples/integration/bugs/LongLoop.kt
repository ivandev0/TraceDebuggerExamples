package org.examples.integration.bugs

import org.junit.jupiter.api.Test

class LongLoop {

    @Test
    fun longLoopTest() {
        val n = 100
        val arr = IntArray(n)
        for (i in 0..n) {
            arr[0] = i * 2
        }
    }
}