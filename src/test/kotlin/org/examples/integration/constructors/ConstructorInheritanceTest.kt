@file:Suppress("unused")

package org.examples.integration.constructors

import kotlin.test.Test

open class ConstructorInheritanceBase {
    val x: Int = 1
}

class ConstructorInheritanceDerived : ConstructorInheritanceBase() {
    val y: Int = 2
}

class ConstructorInheritanceFoo {
    fun foo() {
        ConstructorInheritanceBar().bar()
    }
}

class ConstructorInheritanceBar {
    fun bar() {
        ConstructorInheritanceDerived()
    }
}

class ConstructorInheritanceTest {
    @Test
    fun operation() {
        ConstructorInheritanceFoo().foo()
    }
}
