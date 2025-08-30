package dev.daymor.gradle.component

import io.fuchs.gradle.collisiondetector.DetectCollisionsTask

plugins {
    `java-library`
    id("dev.daymor.gradle.base.dependency-rules")
    id("dev.daymor.gradle.base.identity")
    id("dev.daymor.gradle.base.lifecycle")
    id("dev.daymor.gradle.check.dependencies")
    id("dev.daymor.gradle.check.format-gradle")
    id("dev.daymor.gradle.check.format-java")
    id("dev.daymor.gradle.feature.spring-boot")
    id("dev.daymor.gradle.feature.compile-java")
    id("dev.daymor.gradle.feature.javadoc")
    id("dev.daymor.gradle.feature.publish-java")
    id("dev.daymor.gradle.test.all-test")
}

tasks.named<DetectCollisionsTask>("detectCollisions").configure {
    collisionFilter { exclude("**.html", "**.txt", "LICENSE") }
}
