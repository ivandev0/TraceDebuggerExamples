package org.examples.hackathon

import org.jetbrains.lincheck.test.project.AddressAlreadyExistException
import org.jetbrains.lincheck.test.project.LoadBalancer
import org.jetbrains.lincheck.test.project.RoundRobinStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class LoadBalancerTest {

    @Test
    fun multithreadedGenerationTest() {
        val loadBalancer = LoadBalancer(RoundRobinStrategy())
        val serverSize = 5
        val servers = List(serverSize) { "Server $it" }
        servers.forEach { loadBalancer.add(it) }

        val nThreads = 2
        val perThreadRequests = 2
        val results = List(nThreads) { MutableList<String?>(perThreadRequests) { null } }
        val threads = List(nThreads) { i ->
            thread(start = false) {
                List(perThreadRequests) {
                    loadBalancer.get()
                }.forEachIndexed { j, s ->
                    results[i][j] = s
                }
            }
        }

        threads.forEach { it.start() }
        threads.forEach { it.join() }

        val groupedResult = results.flatten().groupBy { it }

        assertEquals(groupedResult.keys, servers.toSet())
        groupedResult.forEach { (_, list) ->
            assertEquals(nThreads * perThreadRequests / serverSize, list.size)
        }
    }

    @Test
    fun shouldAddAddresses() {
        val loadBalancer = LoadBalancer(RoundRobinStrategy())
        loadBalancer.add("1")
        loadBalancer.add("2")
        loadBalancer.add("3")
    }

    @Test
    fun shouldAddOnlyUniqueAddresses() {
        val loadBalancer = LoadBalancer(RoundRobinStrategy())
        loadBalancer.add("1")
        loadBalancer.add("2")
        assertThrows<AddressAlreadyExistException> { loadBalancer.add("1") }
        assertThrows<AddressAlreadyExistException> { loadBalancer.add("2") }
    }
//
//    @Test
//    fun `should produce round-robin sequence`() {
//        val loadBalancer = LoadBalancer(RoundRobinStrategy())
//
//        val servers = List(5) { "Server $it" }
//        servers.forEach { loadBalancer.add(it) }
//
//        val actualResults = List(servers.size * 10) { loadBalancer.get() }
//        val expectedResult = List(10) { servers }.flatten()
//
//        assertEquals(expectedResult, actualResults)
//    }
//
//    @Test
//    fun `should distribute requests when random strategy is used`() {
//        val random = mockk<Random>()
//        var nextIndex = 0
//        val uniqueSize = 5
//
//        every { random.nextInt(0, uniqueSize) } answers {
//            val index = nextIndex++
//            if (nextIndex == uniqueSize) nextIndex = 0
//            index
//        }
//
//        val loadBalancer = LoadBalancer(RandomStrategy(random))
//
//        val expectedUniqueValues = List(uniqueSize) { "Server $it" }.toHashSet()
//        expectedUniqueValues.forEach { loadBalancer.add(it) }
//
//        assertEqualDistribution(uniqueSize, expectedUniqueValues, loadBalancer)
//        assertEqualDistribution(uniqueSize * 3, expectedUniqueValues, loadBalancer)
//        assertEqualDistribution(uniqueSize * 10, expectedUniqueValues, loadBalancer)
//    }
//
//    private fun assertEqualDistribution(
//        totalRequests: Int,
//        expectedUniqueAddresses: Set<String>,
//        loadBalancer: LoadBalancer
//    ) {
//        check(totalRequests % expectedUniqueAddresses.size == 0) { "Can't randomly distribute requests of that count" }
//
//        val results = List(totalRequests) { loadBalancer.get() }
//        val groupedResult = results.groupBy { it }
//        val requiredCount = totalRequests / expectedUniqueAddresses.size
//
//        assertEquals(expectedUniqueAddresses, groupedResult.keys)
//        groupedResult.forEach { (_, list) -> assertEquals(requiredCount, list.size) }
//    }

}

