plugins {
    alias(libs.plugins.component.framework)
    alias(libs.plugins.dependency.lombok)
}

description =
    "Framework to simplify the creation of a spring boot application. " +
        "The file module api."

publishing.publications.getByName<MavenPublication>("mavenJava") {
    pom.description = description
}

dependencies {
    api(projects.sakuraBootCore)
    api(projects.sakuraBootLogApi)
    api(projects.sakuraBootOpenapiApi)
    api(libs.hibernate.core)
    api(libs.jakarta.persistence.api)
    api(libs.spring.core)
    api(libs.spring.tx)
    api(libs.spring.web)
    implementation(projects.sakuraBootBasicApi)
    implementation(libs.commons.lang3)
}

dependencyAnalysis {
    issues { onIncorrectConfiguration { exclude(libs.hibernate.core) } }
}
