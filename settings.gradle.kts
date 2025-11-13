plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "TraceDebuggerExamples"

// Include additional modules
include(":test-kover")

