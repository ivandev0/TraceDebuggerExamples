package org.examples.integration.bugs

import org.junit.jupiter.api.Test

class LinkedHashSetTest {
    class A(val a: Int)

    @Test
    fun putAnObjectWithoutDefinedHashCode() {
        val linkedHashSet = linkedSetOf(A(1))
        linkedHashSet.add(A(2))
        assert(linkedHashSet.size == 2)
    }
}
