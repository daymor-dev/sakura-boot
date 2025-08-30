package dev.daymor.gradle.base

plugins { id("org.gradlex.jvm-dependency-conflict-resolution") }

jvmDependencyConflicts {
    consistentResolution {
        platform(":versions")
        providesVersions(":aggregation")
    }
}
