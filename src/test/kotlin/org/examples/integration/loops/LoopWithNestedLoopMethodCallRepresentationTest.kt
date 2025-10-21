/*
 * Lincheck
 *
 * Copyright (C) 2019 - 2025 JetBrains s.r.o.
 *
 * This Source Code Form is subject to the terms of the
 * Mozilla Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.examples.integration.loops

import kotlin.test.Test

/**
 * Tests that loops nested across a method call are properly detected
 *
 * Expected result:
 * The outer loop has 2 iterations and records an `outer-…` trace point. For each outer iteration,
 * `methodWithLoop` records a `method-start-…` marker, then an inner loop with 2 iterations
 * (`method-inner-…`), followed by a `method-end-…` marker.
 */
class LoopWithNestedLoopMethodCallRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..2) {
            val a: Any = i
            escape = "outer-$a"
            methodWithLoop(a)
        }
        escape = "END"
    }

    private fun methodWithLoop(a: Any) {
        escape = "method-start-$a"
        for (j in 1..2) {
            val b: Any = j
            escape = "method-inner-$a-$b"
        }
        escape = "method-end-$a"
    }
}
