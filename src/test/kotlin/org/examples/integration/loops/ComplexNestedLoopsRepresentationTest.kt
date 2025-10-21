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

class ComplexNestedLoopsRepresentationTest {
    var escape: Any? = null

    @Test
    fun operation() {
        escape = "START"
        // Outer for loop
        for (i in 1..2) {
            val a: Any = i
            escape = "for-$a"

            // Middle while loop
            var j = 1
            while (j <= 2) {
                val b: Any = j
                escape = "for-$a-while-$b"

                // Inner do-while loop
                var k = 1
                do {
                    val c: Any = k
                    escape = "for-$a-while-$b-dowhile-$c"
                    k++
                } while (k <= 3)

                j++
            }
        }
        escape = "END"
    }
}
