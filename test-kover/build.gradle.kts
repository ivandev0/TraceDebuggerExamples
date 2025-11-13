plugins {
    kotlin("jvm") version "2.2.0"
    id("org.jetbrains.kotlinx.kover") version "0.9.3"
}

apply(plugin = "org.jetbrains.kotlinx.kover")

group = "org.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
