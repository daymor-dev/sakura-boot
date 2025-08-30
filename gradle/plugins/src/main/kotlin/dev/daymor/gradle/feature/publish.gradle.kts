package dev.daymor.gradle.feature

plugins {
    java
    `maven-publish`
    signing
    id("dev.daymor.gradle.base.identity")
}

publishing {
    publications.register<MavenPublication>("mavenJava") {
        from(components["java"])
        versionMapping { allVariants { fromResolutionResult() } }
        pom {
            name = project.name
            inceptionYear = "2024"
            url = "https://sakura-boot.daymor.dev"

            licenses {
                license {
                    name = "The Apache License, Version 2.0"
                    url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    distribution =
                        "http://www.apache.org/licenses/LICENSE-2.0.txt"
                }
            }
            developers {
                developer {
                    id = "malcolm"
                    name = "Malcolm Rozé"
                    email = "sakura-boot@daymor.dev"
                    url = "https://github.com/malcolmR4"
                    organization = "daymor"
                    organizationUrl = "https://github.com/daymor-dev"
                }
            }
            scm {
                url = "https://github.com/daymor-dev/sakura-boot"
                connection =
                    "scm:git:git://github.com/daymor-dev/sakura-boot.git"
                developerConnection =
                    "scm:git:ssh://git@github.com/daymor-dev/sakura-boot.git"
            }
        }
    }
}

signing.sign(publishing.publications["mavenJava"])
