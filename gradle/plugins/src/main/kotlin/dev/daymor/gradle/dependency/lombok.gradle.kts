package dev.daymor.gradle.dependency

import dev.daymor.gradle.util.DependencyUtils.getLibrary

plugins {
    java
    id("dev.daymor.gradle.base.dependency-rules")
}

val libs = versionCatalogs.named("libs")

dependencies {
    compileOnly(getLibrary(libs, "lombok"))
    annotationProcessor(getLibrary(libs, "lombok"))
}

// TODO use lombok plugin from freefair
// but currently does not support cache configuration :
// https://github.com/freefair/gradle-plugins/issues/1059
