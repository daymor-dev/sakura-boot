package dev.daymor.gradle.dependency

import dev.daymor.gradle.util.DependencyUtils.getLibrary

plugins {
    java
    `jvm-test-suite`
    id("dev.daymor.gradle.base.dependency-rules")
    id("dev.daymor.gradle.test.functional-test")
}

val libs = versionCatalogs.named("libs")

dependencies {
    testCompileOnly(getLibrary(libs, "lombok"))
    testAnnotationProcessor(getLibrary(libs, "lombok"))
}
