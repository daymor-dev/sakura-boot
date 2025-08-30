package dev.daymor.gradle.check

import com.autonomousapps.tasks.ProjectHealthTask

plugins {
    java
    id("com.autonomousapps.dependency-analysis")
    id("io.fuchs.gradle.classpath-collision-detector")
    id("dev.daymor.gradle.base.lifecycle")
}

tasks {
    named("qualityCheck") {
        dependsOn(tasks.detectCollisions)
        dependsOn(tasks.withType<ProjectHealthTask>())
    }
    named("qualityGate") {
        dependsOn(tasks.detectCollisions)
        dependsOn(tasks.withType<ProjectHealthTask>())
    }
}
