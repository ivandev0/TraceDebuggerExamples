@file:Suppress("unused")

package org.examples.integration.bugs

import org.junit.jupiter.api.Test

interface Interface {
    fun method()
}

class InterfaceImpl1 : Interface {
    private fun f() {}
    override fun method() {
        f()
    }
}

class InterfaceImpl2 : Interface {
    private fun f() {}
    override fun method() {
        f()
    }
}

class InterfaceImpl3 : Interface {
    private fun f() {}
    override fun method() {
        f()
    }
}

class InterfaceImpl4 : Interface {
    private fun f() {}
    override fun method() {
        f()
    }
}

val classLoader: ClassLoader = Interface::class.java.classLoader

class MyClassLoader : ClassLoader(classLoader) {
    public override fun loadClass(name: String, resolve: Boolean): Class<*> {
        return super.loadClass(name, resolve)
    }

    public override fun findClass(moduleName: String, name: String): Class<*> {
        return super.findClass(moduleName, name)
    }

    override fun loadClass(name: String): Class<*> {
        return super.loadClass(name)
    }
}


fun getClassName(index: Int) = "org.examples.integration.bugs.InterfaceImpl$index"
fun getInterface1(): Interface = Class.forName(getClassName(1)).getDeclaredConstructor().newInstance() as Interface
fun getInterface2(): Interface = classLoader.loadClass(getClassName(2)).getDeclaredConstructor().newInstance() as Interface
fun getInterface3(): Interface = MyClassLoader().loadClass(getClassName(3)).getDeclaredConstructor().newInstance() as Interface
fun getInterface4(): Interface = MyClassLoader().loadClass(getClassName(4), true).getDeclaredConstructor().newInstance() as Interface


class InterfaceTest {
    /**
     * Problem: trace recorder doesn't instrument `InterfaceImpl{N}` when it is accessed dynamically via class loading.
     * Expected: for i1..i4 show test -> getInterface{N} -> InterfaceImpl{N}.method -> f.
     */
    @Test
    fun test() {
        val i1 = getInterface1()
        i1.method()
        val i2 = getInterface2()
        i2.method()
        val i3 = getInterface3()
        i3.method()
        val i4 = getInterface4()
        i4.method()
    }
}