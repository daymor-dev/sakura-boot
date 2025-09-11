plugins { alias(libs.plugins.component.framework) }

description =
    "Framework to simplify the creation of a spring boot application. " +
        "The file test functionalities."

publishing.publications.getByName<MavenPublication>("mavenJava") {
    pom.description = description
}

dependencies {
    api(projects.sakuraBootCore)
    api(projects.sakuraBootCoreTest)
    api(projects.sakuraBootFileApi)
    implementation(libs.assertj.core)
    implementation(libs.commons.lang3)
    implementation(libs.mockito.core)
    implementation(libs.spring.core)
    implementation(libs.spring.web)
    compileOnly(libs.jakarta.servlet.api)
    compileOnly(libs.spring.data.jpa)
    runtimeOnly(libs.hibernate.core)
    runtimeOnly(libs.junit.jupiter.api)
}
