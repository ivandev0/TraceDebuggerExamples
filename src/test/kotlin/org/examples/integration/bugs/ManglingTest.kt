package org.examples.integration.bugs

import org.junit.jupiter.api.Test

/**
 * These tests check how mangled declarations are shown in trace.
 */
class ManglingTest {
    internal inline fun inlineFunction(x: Int, y: Int, f: (Int, Int) -> Int) = f(x, y)
    internal inline fun inlineFunction(x: UInt, y: UInt, f: (UInt, UInt) -> UInt) = f(x, y)
    internal inline fun inlineFunctionWithDefault(x: UInt, y: UInt = 10U, f: (UInt, UInt) -> UInt) = f(x, y)

    @Test
    fun testInline() {
        println(inlineFunction(2, 3) { x, y -> x + y })
    }

    @Test
    fun testPrintln() {
        println("Hello world")
    }

    @Test
    fun testRepeat() {
        repeat(10) {
            println("Hello world")
        }
    }

    @Test
    fun testInlineLambda() {
        2
            .let { println(it + it) }
    }

    internal fun defaultArg(x: Int = 3) {
        println(x)
    }

    @Test
    fun testDefaultArg() {
        defaultArg()
        defaultArg(4)
        defaultArg()
        defaultArg(4)
    }

    internal fun UInt.sum(x: UInt) = this + x

    @Test
    fun testInlineClass() {
        val x: Any = 2U.sum(3U)
        println(x)
        println((x as UInt).sum(4U))
    }

    internal inner class Inner {
        internal inner class Deeper {
            internal fun f(): String {
                val outer = this@ManglingTest
                val inner = this@Inner
                val deeper = this
                return "$outer:$inner:$deeper"
            }
        }
    }

    @Test
    fun testInner() {
        println(Inner().Deeper().f())
    }

    @Test
    fun testInlineClassInlineFunction() {
        println(inlineFunction(2U, 3U) { x, y -> x + y })
    }

    @Test
    fun testInlineClassInlineFunctionWithDefault() {
        println(inlineFunctionWithDefault(2U, 3U) { x, y -> x + y })
        println(inlineFunctionWithDefault(2U) { x, y -> x + y })
    }

    @Test
    fun testCapture() {
        val multi = 2
        fun multiply(x: Int) = x * multi
        class A {
            fun multiply(x: Int) = x * multi
        }
        println(multiply(3))
        println(A().multiply(3))
    }

    @JvmInline
    value class Wrapper(val x: Int) {
        context(a: Wrapper, _: Int)
        fun Wrapper.f(): String {
            val wrapper1 = this
            val wrapper2 = a
            val wrapper3 = this@Wrapper
            val int = contextOf<Int>()
            return "$wrapper1 $wrapper2 $wrapper3 $int"
        }
    }

    @Test
    fun testReceivers() {
        with(Wrapper(1)) {
            context(Wrapper(2), 3) {
                println(Wrapper(4).f())
            }
        }
    }

    @Test
    fun testInternalViaAccessor() {
        A.requiresAccessor()
    }
    
    @Test
    fun testNestedInlineFunction() {
        outerInlineFunction(2U, 3U) { a, b -> a + b }
    }

    internal inline fun outerInlineFunction(x: UInt, y: UInt, f: (UInt, UInt) -> UInt) =
        inlineFunction(x, y, f)
}

internal object A {
    internal fun requiresAccessor() {}
}
