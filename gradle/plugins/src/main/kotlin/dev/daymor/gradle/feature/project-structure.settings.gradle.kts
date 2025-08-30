package dev.daymor.gradle.feature

import dev.daymor.gradle.util.ProjectUtils.includeDir

dependencyResolutionManagement.repositories.mavenCentral()

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeDir(3)
