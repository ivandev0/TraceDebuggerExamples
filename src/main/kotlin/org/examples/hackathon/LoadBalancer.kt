package org.jetbrains.lincheck.test.project

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.random.Random


sealed interface Strategy {
    fun nextIndex(addresses: List<String>): Int
}

class RoundRobinStrategy: Strategy {
    private var index = 0
    override fun nextIndex(addresses: List<String>): Int {
        return (index++) % addresses.size
    }
}

class RandomStrategy(private val random: Random): Strategy {
    override fun nextIndex(addresses: List<String>): Int {
        return addresses.indices.random(random)
    }
}

class LoadBalancer(
    private val strategy: Strategy,
) {

    private val lock = ReentrantLock()
    private val addresses = mutableListOf<String>()

    fun add(address: String) = lock.withLock {
        if (address in addresses) throw AddressAlreadyExistException()

        addresses += address
    }

    fun get(): String = lock.withLock {
        if (addresses.isEmpty()) throw NoAddressesAddedException()

        val index = strategy.nextIndex(addresses)
        return addresses[index]
    }

}

class AddressAlreadyExistException: Exception()
class NoAddressesAddedException: Exception()

class RateLimiter(
    val timeProvider: () -> Long,
    val period: Long,
    val limit: Int
) {

    private val currentBucket = AtomicReference(TimeBucket(0))

    fun getPermission(): Boolean {
        val currentTime = timeProvider()
        while (true) {
            val bucket = currentBucket.get()
            if (bucket.isExpired(currentTime)) {
                val newBucket = TimeBucket(currentTime)
                if (currentBucket.compareAndSet(bucket, newBucket)) {
                    return true
                } else continue
            }
            return bucket.getPermission() && bucket === currentBucket.get()
        }
    }

    inner class TimeBucket(
        private val firstPermittedTime: Long
    ) {
        private val permitted = AtomicInteger(1)
        fun isExpired(currentTime: Long): Boolean = currentTime - firstPermittedTime > period
        fun getPermission(): Boolean = permitted.incrementAndGet() <= limit
    }
}