package org.examples.hackathon

import org.junit.jupiter.api.Test

class HashMapTest {

    var data = 1
    var k: MutableMap<Int, Int>? = null

    @Test
    fun test() {
        var i = 1
        var s = data + 1

        data = 6
        k = HashMap()
        k!![1] = 1

        val m = HashMap<Int, String>()
        m[1] = "123"
        m[2] = "ABOBA"
        m[3] = "1wad23"
    }

}