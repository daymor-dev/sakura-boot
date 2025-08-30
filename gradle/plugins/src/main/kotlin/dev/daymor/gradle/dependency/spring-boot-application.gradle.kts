package dev.daymor.gradle.dependency

import dev.daymor.gradle.util.DependencyUtils.getLibrary

plugins {
    java
    id("org.springframework.boot")
    id("dev.daymor.gradle.base.dependency-rules")
}

configurations {
    productionRuntimeClasspath {
        extendsFrom(configurations["internal"])
        shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
    }
    developmentOnly {
        extendsFrom(configurations["internal"])
        shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
    }
}

val libs = versionCatalogs.named("libs")

dependencies {
    compileOnly(getLibrary(libs, "jsr305"))
    developmentOnly(getLibrary(libs, "spring-boot-devtools"))
}
