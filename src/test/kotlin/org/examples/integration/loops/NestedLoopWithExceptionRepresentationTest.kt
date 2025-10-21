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
 * Tests that an exception inside nested loops is properly detected and propagated to the caller.
 *
 * Expected result:
 * The nested method records `METHOD_START`, iterates `i = 1..2` and `j = 1..3`, and throws when
 * `i == 2 && j == 2`. The method does not reach `METHOD_END`.
 * The caller catches the exception, records `CAUGHT EXCEPTION`, and then records `END`.
 */
class NestedLoopWithExceptionRepresentationTest {
    var escape: Any? = null

    private fun nestedLoopWithException() {
        escape = "METHOD_START"
        for (i in 1..2) {
            val a: Any = i
            for (j in 1..3) {
                escape = "$a.$j"
                if (i == 2 && j == 2) {
                    throw RuntimeException("Exception in nested loop")
                }
            }
        }
        escape = "METHOD_END"
    }

    @Test
    fun operation() {
        escape = "START"
        try {
            nestedLoopWithException()
        } catch (e: RuntimeException) {
            escape = "CAUGHT EXCEPTION"
        }
        escape = "END"
    }
}
