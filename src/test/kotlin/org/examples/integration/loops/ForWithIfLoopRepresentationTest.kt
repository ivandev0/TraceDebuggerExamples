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
 * Tests that a long-running loop with conditional extra trace points is properly detected
 *
 * Expected result:
 * The loop performs 10 full iterations (breaks when `total > 10`). Each iteration records
 * `a.toString()`. When `i` cycles to 0 (every 3rd step), an additional trace point with
 * the prefix `"%3-"` is recorded in that iteration.
 */
class ForWithIfLoopRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        var i = 0
        var total = 0
        while (true) {
            total++
            if (total > 10) {
                break
            }
            val a: Any = i
            escape = a.toString()
            i = (i + 1) % 3
            if (i == 0) {
                escape = "%3-" + escape
            }
        }
        escape = "END"
    }
}
