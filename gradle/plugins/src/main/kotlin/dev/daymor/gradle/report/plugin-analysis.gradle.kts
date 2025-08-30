package dev.daymor.gradle.report

import dev.daymor.gradle.task.PluginApplicationOrderAnalysis

tasks.register<PluginApplicationOrderAnalysis>(
    "analysePluginApplicationOrder"
) {
    group = "help"

    pluginSrcFolders.from(
        project.layout.projectDirectory.dir("../plugins/src/main/kotlin")
    )
    pluginApplicationDiagram =
        layout.buildDirectory.file(
            "reports/plugins/plugin-application-order.puml"
        )
}
