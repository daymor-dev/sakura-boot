package dev.daymor.gradle.check

plugins {
    id("com.diffplug.spotless")
    id("dev.daymor.gradle.base.lifecycle")
}

spotless.java {
    eclipse()
        .configFile(
            "$rootDir/sakura-boot-checkstyle/formatter/java-formatter.xml"
        )
}

tasks {
    named("qualityCheck") { dependsOn(tasks.spotlessCheck) }
    named("qualityGate") { dependsOn(tasks.spotlessApply) }
}
