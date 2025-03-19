package org.examples.hackathon

import org.junit.jupiter.api.Test

class SetterGetterInlineTest {

    @Test
    fun test() {
        val o = Obj()
        o.a = 1
        println("Hello, world!")
        val temp = o.a
        o.a = temp + 10
        println(o.a)
    }

}

class Obj {
    var a: Int = 0
}
