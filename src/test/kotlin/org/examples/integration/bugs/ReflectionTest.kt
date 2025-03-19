package org.examples.integration.bugs

import org.junit.jupiter.api.Test
import java.lang.reflect.Method
import java.util.*

class ReflectionTest {
    fun foo() {}

    @Test
    fun sortMethods() {
        val declaredMethods = ReflectionTest::class.java.declaredMethods
        Arrays.sort(declaredMethods, DEFAULT)
        assert(declaredMethods.size == 3)
    }

    private val DEFAULT: Comparator<Method> = Comparator { m1, m2 ->
        val i1 = m1.name.hashCode()
        val i2 = m2.name.hashCode()
        return@Comparator if (i1 < i2) -1 else 1
    }
}
