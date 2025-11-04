package org.examples.integration.bugs

import org.junit.jupiter.api.Test

interface Interface {
    fun method()
}

class InterfaceImpl : Interface {
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


const val className = "org.examples.integration.bugs.InterfaceImpl"
fun getInterface1(): Interface = Class.forName(className).getDeclaredConstructor().newInstance() as Interface
fun getInterface2(): Interface = classLoader.loadClass(className).getDeclaredConstructor().newInstance() as Interface
fun getInterface3(): Interface = MyClassLoader().loadClass(className).getDeclaredConstructor().newInstance() as Interface
fun getInterface4(): Interface = MyClassLoader().loadClass(className, true).getDeclaredConstructor().newInstance() as Interface


class InterfaceTest {
    /**
     * Problem: trace sometimes doesn't step from `Interface.method()` into `InterfaceImpl.method()` (and `f()`)
     * when the impl class is obtained via different class loading paths.
     * Expected: for i1..i4 show test -> getInterface{N} -> InterfaceImpl.method -> f; class loader must not matter.
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