package dev.daymor.gradle.base

plugins {
    base
    id("dev.daymor.gradle.base.lifecycle")
}

defaultTasks("tasks")

tasks.named<TaskReportTask>("tasks") { displayGroup = "build" }
