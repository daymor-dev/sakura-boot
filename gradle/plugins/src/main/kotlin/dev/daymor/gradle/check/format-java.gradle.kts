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

    custom("javadoc-empty-line-before-p") { str ->
        str.replace(Regex("""(\s*\*\s.*)(\r?\n)(\s*\*\s<p>)"""), "$1$2 *$2$3")
    }

    custom("javadoc-line-has-trailing-spaces") { str ->
        str.replace(Regex("""(\s*\*.*?)[ \t]+(\r?\n)"""), "$1$2")
    }
}

tasks {
    named("qualityCheck") { dependsOn(tasks.spotlessCheck) }
    named("qualityGate") { dependsOn(tasks.spotlessApply) }
}
