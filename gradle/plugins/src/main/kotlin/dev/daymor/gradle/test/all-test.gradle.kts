package dev.daymor.gradle.test

plugins {
    java
    `jvm-test-suite`
    id("dev.daymor.gradle.test.test")
    id("dev.daymor.gradle.test.integration-test")
    id("dev.daymor.gradle.test.functional-test")
    id("dev.daymor.gradle.test.performance-test")
}

tasks.create("allTest") {
    group = "verification"
    dependsOn(testing.suites.named("test"))
    dependsOn(testing.suites.named("integrationTest"))
    dependsOn(testing.suites.named("functionalTest"))
    dependsOn(testing.suites.named("performanceTest"))
}
