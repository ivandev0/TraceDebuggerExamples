package org.examples.hackathon

import org.junit.jupiter.api.Test

class CycleTest {
    var y = 1
    @Test
    fun test() {
        for (i in 0..1) {
            println(i)
        }
        y = 1
        func1()
        func2()
    }

    fun func1() {
        var a = 10
        val c = a * 10
        println("func1 $c")

        func2()
    }

    fun func2() {
        println("func2")
    }
}