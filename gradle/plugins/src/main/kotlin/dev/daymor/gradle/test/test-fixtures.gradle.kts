package dev.daymor.gradle.test

plugins { `java-test-fixtures` }

tasks.testFixturesJar { setGroup(null) }
