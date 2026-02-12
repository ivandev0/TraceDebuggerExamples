package org.examples.integration.attach

import org.junit.jupiter.api.Test

class DynamicAttachTest {
    fun operation() {
        var x = 0
        for (i in 1..10) {
            Thread.sleep(100)

            x = i
            println("Iteration $x")
        }
    }

    @Test
    fun test() {
        // get some time for the recorder to attach
        Thread.sleep(5_000)

        println("Starting operation...")

        // The test body has to be in a separate method so that the attached javaagent can instrument it;
        // methods currently active on the stack (i.e., `test()` in our case) are not instrumented.
        operation()

        println("Finished operation...")
    }
}