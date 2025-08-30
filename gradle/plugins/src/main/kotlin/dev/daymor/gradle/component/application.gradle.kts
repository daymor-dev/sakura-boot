package dev.daymor.gradle.component

import io.fuchs.gradle.collisiondetector.DetectCollisionsTask

plugins {
    application
    id("dev.daymor.gradle.base.dependency-rules")
    id("dev.daymor.gradle.base.lifecycle")
    id("dev.daymor.gradle.check.dependencies")
    id("dev.daymor.gradle.check.format-gradle")
    id("dev.daymor.gradle.check.format-java")
    id("dev.daymor.gradle.feature.spring-boot-application")
    id("dev.daymor.gradle.feature.compile-java")
    id("dev.daymor.gradle.feature.javadoc")
    id("dev.daymor.gradle.test.all-test")
}

tasks.named<DetectCollisionsTask>("detectCollisions").configure {
    collisionFilter { exclude("**.html", "**.txt", "LICENSE") }
}
