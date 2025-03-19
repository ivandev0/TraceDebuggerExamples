package org.examples.hackathon

import org.junit.jupiter.api.Test

class BigLongTest {

    val list = ArrayList<Double>()

    @Test
    fun test() {
        list += 1.0
        repeat(10) { list += 0.0 }

        repeat(100) {
//            val pow = it.toDouble().pow(7.2)
//            val base = pow % 6
//            val value = log(base, 2.0)
            list[it % list.size] = 1.0
        }
        println(list)
    }


}