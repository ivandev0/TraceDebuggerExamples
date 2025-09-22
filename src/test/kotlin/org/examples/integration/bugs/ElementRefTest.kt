package org.examples.integration.bugs

import org.junit.jupiter.api.Test

class ElementRefTest {

    @Test
    fun accessLocalVariableFromLambda() {
        var localVariable = 1
        val modify = {
            localVariable++
        }

        modify()
        modify()
        assert(localVariable == 3)
    }
}