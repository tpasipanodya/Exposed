import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.exposed.gradle.Versions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply true
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    api(project(":exposed-core"))
    api(project(":exposed-dao"))
    api(project(":spring-transaction"))
    api("org.springframework.boot", "spring-boot-starter-data-jdbc", Versions.springBoot)
    api("org.springframework.boot", "spring-boot-autoconfigure", Versions.springBoot)
    compileOnly("org.springframework.boot", "spring-boot-configuration-processor", Versions.springBoot)

    testImplementation("org.springframework.boot", "spring-boot-starter-test", Versions.springBoot)
    // put in testImplementation so no hard dependency for those using the starter
    testImplementation("org.springframework.boot", "spring-boot-starter-webflux", Versions.springBoot)
    testImplementation("com.h2database", "h2", Versions.h2_v2)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test>().configureEach {
    testLogging {
        events.addAll(listOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED))
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }
    useJUnitPlatform()
}
