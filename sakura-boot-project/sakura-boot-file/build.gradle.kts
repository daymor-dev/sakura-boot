plugins { alias(libs.plugins.component.framework) }

description =
    "Framework to simplify the creation of a spring boot application. " +
        "The file functionalities."

publishing.publications.getByName<MavenPublication>("mavenJava") {
    pom.description = description
}

dependencies {
    api(projects.sakuraBootFileApi)
    api(libs.jakarta.persistence.api)
    api(libs.spring.core)
    api(libs.spring.tx)
    implementation(projects.sakuraBootCore)
}
