repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    gradleApi()
    implementation("org.jetbrains.kotlin.jvm", "org.jetbrains.kotlin.jvm.gradle.plugin", "1.6.10")
    implementation("com.avast.gradle", "gradle-docker-compose-plugin", "0.14.9")
    implementation("io.gitlab.arturbosch.detekt", "detekt-gradle-plugin", "1.19.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "16"
        apiVersion = "1.5"
        languageVersion = "1.5"
    }
}

plugins {
    `kotlin-dsl` apply true
}

gradlePlugin {
    plugins {
        create("testWithDBs") {
            id = "testWithDBs"
            implementationClass = "org.jetbrains.exposed.gradle.DBTestingPlugin"
        }
    }
}
