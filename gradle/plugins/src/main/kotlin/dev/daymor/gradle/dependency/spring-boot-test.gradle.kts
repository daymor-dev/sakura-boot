package dev.daymor.gradle.dependency

import dev.daymor.gradle.util.DependencyUtils.getLibrary

plugins {
    java
    id("dev.daymor.gradle.base.dependency-rules")
}

val libs = versionCatalogs.named("libs")

dependencies { testCompileOnly(getLibrary(libs, "jsr305")) }
