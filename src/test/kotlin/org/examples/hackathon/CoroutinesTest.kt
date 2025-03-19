package org.examples.hackathon

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CoroutinesTest {

    @Test
    fun test() = runBlocking {
        val c = Channel<String>()
        launch {
            val message = c.receive()
            println("Message: $message")
        }
        c.send("hello, world!")
    }
}