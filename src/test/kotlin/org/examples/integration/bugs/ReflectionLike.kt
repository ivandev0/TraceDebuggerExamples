package org.examples.integration.bugs

import org.junit.jupiter.api.Test
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.AbstractMap.SimpleEntry
import kotlin.reflect.jvm.javaMethod

private val topLevelHelper = TopLevelHelper()

fun staticTopLevelTarget(a: Int, b: Int): Int {
    topLevelHelper.placeholder()
    return a * 2 + b
}

class TopLevelHelper {
    fun placeholder() {
        // Placeholder for top-level function
    }
}

abstract class ReflectionInvokeBase {
    private val helper = Helper()

    fun targetReflection(a: Int, b: Int): Int {
        helper.placeholder()
        return a + b
    }

    fun targetMethodHandle(a: Int, b: Int): Int {
        helper.placeholder()
        return a * b
    }

    fun targetKotlinReflection(a: Int, b: Int): Int {
        helper.placeholder()
        return a - b
    }

    fun targetKotlinReflectionCallBy(a: Int, b: Int): Int {
        helper.placeholder()
        return a + b + 1
    }

    fun targetThrowsException(a: Int, b: Int): Int {
        helper.throwException()
        return a + b
    }

    class Helper {
        fun placeholder() {
            // Placeholder method to verify nested call tracking
        }

        fun throwException() {
            throw IllegalStateException("Test exception from nested call")
        }
    }

    class ReflectionTarget(val value: Int) {
        private val helper = Helper()

        init {
            helper.placeholder()
        }
    }
}

class ReflectionMethodInvokeRepresentationTest :
    ReflectionInvokeBase() {
    private val targetReflectionMethod = javaClass.getMethod(
        "targetReflection",
        Int::class.javaPrimitiveType!!,
        Int::class.javaPrimitiveType!!
    )

    private fun invokeViaReflection(a: Int, b: Int): Int {
        return targetReflectionMethod.invoke(this, a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaReflection(1, 2)
    }
}

class ReflectionStaticMethodInvokeRepresentationTest :
    ReflectionInvokeBase() {
    private val targetReflectionMethod = this::class.java.getMethod(
        "staticTarget",
        Int::class.javaPrimitiveType!!,
        Int::class.javaPrimitiveType!!
    )

    companion object {
        private val helper = Helper()

        @JvmStatic
        @Suppress("unused")
        fun staticTarget(a: Int, b: Int): Int {
            helper.placeholder()
            return a + b + 2
        }

        class Helper {
            fun placeholder() {
                // Placeholder for static method
            }
        }
    }

    private fun invokeViaReflection(a: Int, b: Int): Int {
        return targetReflectionMethod.invoke(null, a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaReflection(1, 2)
    }
}

class MethodHandleInvokeWithArgumentsVarargsRepresentationTest :
    ReflectionInvokeBase() {
    private val targetMethodHandle = MethodHandles.lookup().findVirtual(
        javaClass,
        "targetMethodHandle",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    private fun invokeViaMethodHandleVarargs(a: Int, b: Int): Int {
        return targetMethodHandle.invokeWithArguments(this, a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleVarargs(3, 4)
    }
}

class MethodHandleInvokeStaticRepresentationTest :
    ReflectionInvokeBase() {
    private val targetMethodHandle = MethodHandles.lookup().findStatic(
        this::class.java,
        "staticTarget",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    companion object {
        private val helper = Helper()

        @JvmStatic
        @Suppress("unused")
        fun staticTarget(a: Int, b: Int): Int {
            helper.placeholder()
            return a * 3 + b
        }

        class Helper {
            fun placeholder() {
                // Placeholder for static method
            }
        }
    }

    private fun invokeViaMethodHandleInvokeExact(a: Int, b: Int): Int {
        return targetMethodHandle.invokeExact(a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleInvokeExact(3, 4)
    }
}

class MethodHandleInvokeStaticTopLevelRepresentationTest :
    ReflectionInvokeBase() {
    private val topLevelOwnerClass = requireNotNull(::staticTopLevelTarget.javaMethod).declaringClass

    private val targetMethodHandle = MethodHandles.lookup().findStatic(
        topLevelOwnerClass,
        "staticTopLevelTarget",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    private fun invokeViaMethodHandleInvoke(a: Int, b: Int): Int {
        return targetMethodHandle.invoke(a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleInvoke(5, 6)
    }
}

class MethodHandleInvokeWithArgumentsListRepresentationTest :
    ReflectionInvokeBase() {
    private val targetMethodHandle = MethodHandles.lookup().findVirtual(
        javaClass,
        "targetMethodHandle",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    private fun invokeViaMethodHandleList(a: Int, b: Int): Int {
        return targetMethodHandle.invokeWithArguments(listOf(this, a, b)) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleList(5, 6)
    }
}

class MethodHandleInvokeRepresentationTest :
    ReflectionInvokeBase() {
    private val targetMethodHandle = MethodHandles.lookup().findVirtual(
        javaClass,
        "targetMethodHandle",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    private fun invokeViaMethodHandleInvoke(a: Int, b: Int): Int {
        return targetMethodHandle.invoke(this, a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleInvoke(7, 8)
    }
}

class MethodHandleInvokeExactRepresentationTest :
    ReflectionInvokeBase() {
    private val targetMethodHandle = MethodHandles.lookup().findVirtual(
        javaClass,
        "targetMethodHandle",
        MethodType.methodType(
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!,
            Int::class.javaPrimitiveType!!
        )
    )

    private fun invokeViaMethodHandleInvokeExact(a: Int, b: Int): Int {
        return targetMethodHandle.invokeExact(this, a, b) as Int
    }

    @Test
    fun operation() {
        invokeViaMethodHandleInvokeExact(9, 10)
    }
}

class KotlinReflectionCallRepresentationTest :
    ReflectionInvokeBase() {
    private val targetKotlinFunction = ReflectionInvokeBase::targetKotlinReflection

    private fun invokeViaKotlinReflection(a: Int, b: Int): Int {
        return targetKotlinFunction.call(this, a, b)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflection(11, 12)
    }
}

class KotlinReflectionCallByRepresentationTest :
    ReflectionInvokeBase() {
    private val targetKotlinCallByFunction = ReflectionInvokeBase::targetKotlinReflectionCallBy

    private fun invokeViaKotlinReflectionCallBy(a: Int, b: Int): Int {
        val params = targetKotlinCallByFunction.parameters.let { (p1, p2, p3) ->
            ListMap(
                SimpleEntry(p1, this), SimpleEntry(p2, a),
                SimpleEntry(p3, b)
            )
        }
        return targetKotlinCallByFunction.callBy(params)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflectionCallBy(13, 14)
    }
}

class KotlinReflectionConstructorCallRepresentationTest :
    ReflectionInvokeBase() {
    val ctor = ReflectionTarget::class.constructors.single()
    private fun invokeViaKotlinReflectionConstructor(value: Int): ReflectionTarget {
        return ctor.call(value)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflectionConstructor(16)
    }
}

class KotlinReflectionConstructorCallByRepresentationTest :
    ReflectionInvokeBase() {
    val ctor = ReflectionTarget::class.constructors.single()
    private fun invokeViaKotlinReflectionConstructorCallBy(value: Int): ReflectionTarget {
        val params = ctor.parameters.let { (p1) ->
            mapOf(p1 to value)
        }
        return ctor.callBy(params)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflectionConstructorCallBy(17)
    }
}

class KotlinReflectionStaticTopLevelCallRepresentationTest :
    ReflectionInvokeBase() {
    val fn = ::staticTopLevelTarget
    private fun invokeViaKotlinReflectionStatic(a: Int, b: Int): Int {
        return fn.call(a, b)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflectionStatic(18, 19)
    }
}

// Map not relying on hash codes
class ListMap<K, V>(vararg val elements: SimpleEntry<K, V>) : AbstractMap<K, V>() {
    override val entries: Set<Map.Entry<K, V>> = object : AbstractSet<Map.Entry<K, V>>() {
        override val size: Int = elements.size
        override fun iterator() = elements.iterator()
    }
}

class KotlinReflectionStaticTopLevelCallByRepresentationTest :
    ReflectionInvokeBase() {
    val fn = ::staticTopLevelTarget
    private fun invokeViaKotlinReflectionStaticCallBy(a: Int, b: Int): Int {
        val params = fn.parameters.let { (p1, p2) ->
            ListMap(SimpleEntry(p1, a), SimpleEntry(p2, b))
        }
        return fn.callBy(params)
    }

    @Test
    fun operation() {
        invokeViaKotlinReflectionStaticCallBy(20, 21)
    }
}

class ReflectionConstructorInvokeRepresentationTest :
    ReflectionInvokeBase() {
    private val reflectionTargetConstructor = ReflectionTarget::class.java.getConstructor(
        Int::class.javaPrimitiveType!!
    )

    private fun invokeViaReflectionConstructor(value: Int): ReflectionTarget {
        return reflectionTargetConstructor.newInstance(value)
    }

    @Test
    fun operation() {
        invokeViaReflectionConstructor(15)
    }
}

class MethodHandleInvokeConstructorRepresentationTest :
    ReflectionInvokeBase() {
    private val targetConstructorHandle = MethodHandles.lookup().findConstructor(
        ReflectionTarget::class.java,
        MethodType.methodType(Void.TYPE, Int::class.javaPrimitiveType!!)
    )

    private fun invokeViaMethodHandleConstructor(value: Int): ReflectionTarget {
        return targetConstructorHandle.invokeExact(value) as ReflectionTarget
    }

    @Test
    fun operation() {
        invokeViaMethodHandleConstructor(22)
    }
}

class ReflectionExceptionRepresentationTest :
    ReflectionInvokeBase() {
    private val targetReflectionMethod = javaClass.getMethod(
        "targetThrowsException",
        Int::class.javaPrimitiveType!!,
        Int::class.javaPrimitiveType!!
    )

    private fun invokeViaReflection(a: Int, b: Int): Int {
        return try {
            targetReflectionMethod.invoke(this, a, b) as Int
        } catch (_: Exception) {
            -1 // Return -1 on exception
        }
    }

    @Test
    fun operation() {
        invokeViaReflection(1, 2)
    }
}
