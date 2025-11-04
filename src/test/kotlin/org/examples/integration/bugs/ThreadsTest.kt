package org.examples.integration.bugs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread


class ThreadsTest {
    var field = Any()

    @Test
    fun daemonThreadTest() {
        val inc = AtomicInteger(0)

        thread {
            repeat(100) {
                if (inc.get() == 10) {
                    // hang
                    Thread.sleep(1_000_000_000)
                }
                field = "Finish iteration $it"
                inc.incrementAndGet()
            }
        }

        while (inc.get() != 10) {
            // make sure daemon has enough time to increase `inc` up to 10
            // so that Main has deterministic iterations count - just one
            Thread.sleep(100)
        }
        field = "Complete Main thread"
    }

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