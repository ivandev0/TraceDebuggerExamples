@file:Suppress("unused")

package org.examples.integration.constructors

import kotlin.test.Test

open class ConstructorInheritanceWithExceptionBase {
    val x: Int = 1
    init {
        throw Exception("Base class initialization exception")
    }
}

class ConstructorInheritanceWithExceptionDerived : ConstructorInheritanceWithExceptionBase() {
    val y: Int = 2
}

class ConstructorInheritanceWithExceptionFoo {
    fun foo() {
        ConstructorInheritanceWithExceptionBar().bar()
    }
}

class ConstructorInheritanceWithExceptionBar {
    fun bar() {
        ConstructorInheritanceWithExceptionDerived()
    }
}

class ConstructorInheritanceWithExceptionTest {
    @Test
    fun operation() {
        try {
            ConstructorInheritanceWithExceptionFoo().foo()
        } catch (e: Exception) {
            check(e.message == "Base class initialization exception") {
                "Expected to get an exception from the base class construct"
            }
        }
    }
}
