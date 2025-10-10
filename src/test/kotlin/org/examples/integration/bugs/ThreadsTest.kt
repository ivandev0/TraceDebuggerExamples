package org.examples.integration.bugs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture

class ThreadsTest {

    @Test
    fun forkJoinPoolTest() {
        val future = CompletableFuture.supplyAsync {
            println("Running on Thread ${Thread.currentThread().name}")
            Thread.sleep(200)
            42
        }

        // Do not provide executor -> uses ForkJoinPool.commonPool() (background threads)
        val result = future.get()
        println("Result: $result")
    }

    @Test
    fun coroutinesTest() {
        runBlocking(Dispatchers.IO) {
            val n = 3
            val q = Channel<Int>(capacity = 1)

            val sender = launch {
                for (i in 0 until n) {
                    q.send(i)
                }
            }
            val receiver = launch {
                for (i in 0 until n) {
                    val next = q.receive()
                    check(next == i)
                }
            }

            sender.join()
            receiver.join()
        }
    }
}