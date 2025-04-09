package org.examples.integration.bugs

import org.junit.jupiter.api.Test
import java.lang.reflect.Method
import java.util.*

class ReflectionTest {
    class A(val a: Int) {
        fun foo() {}
        private fun bar() {}
        internal suspend fun baz() {}
    }

    @Test
    fun sortMethods() {
        val declaredMethods = A::class.java.declaredMethods
        Arrays.sort(declaredMethods, methodComparator)
        assert(declaredMethods.size == 4)
    }

    // Must be kept as an anonymous object to keep the test in Linheck stable.
    // If converted to lambda, we can have a different name for the function with each run.
    @Suppress("ObjectLiteralToLambda")
    private val methodComparator: Comparator<Method> = object : Comparator<Method> {
        override fun compare(m1: Method, m2: Method): Int {
            val i1 = m1.name.hashCode()
            val i2 = m2.name.hashCode()
            return if (i1 < i2) -1 else 1
        }
    }
}
