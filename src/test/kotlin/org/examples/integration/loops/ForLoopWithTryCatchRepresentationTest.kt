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
 * Tests that `for` loop with try/catch in the body is properly detected
 *
 * Expected result:
 * Trace contains a loop with 3 iterations. On i==2, the try block throws and the
 * catch branch is recorded; on other iterations only the try branch is recorded.
 */
class ForLoopWithTryCatchRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        for (i in 1..3) {
            val a: Any = i
            try {
                if (i == 2) {
                    throw RuntimeException("Exception in loop")
                }
                escape = "try-$a"
            } catch (e: RuntimeException) {
                escape = "catch-$a"
            }
        }
        escape = "END"
    }
}
