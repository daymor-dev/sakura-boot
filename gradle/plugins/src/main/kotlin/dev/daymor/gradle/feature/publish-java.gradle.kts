package dev.daymor.gradle.feature

plugins { id("dev.daymor.gradle.feature.publish") }

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    named("sourcesJar") { group = null }
    named("javadocJar") { group = null }
}
