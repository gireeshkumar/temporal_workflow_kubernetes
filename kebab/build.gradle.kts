plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.allopen") version "1.8.22"
    id("io.quarkus")
    id("com.google.protobuf") version "0.9.4"
    id("org.graalvm.buildtools.native") version "0.9.24"
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

val quarkusPlatformGroupId: String? by project
val quarkusPlatformArtifactId: String? by project
val quarkusPlatformVersion: String? by project

dependencies {
    implementation("io.quarkiverse.microprofile:quarkus-microprofile:3.0.0.Final")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("io.temporal:temporal-sdk:1.18.2")
    implementation("io.temporal:temporal-kotlin:1.21.0")

    implementation("org.jboss.logging:commons-logging-jboss-logging")
    implementation("org.jboss.logmanager:log4j-jboss-logmanager")
    implementation("org.jboss.slf4j:slf4j-jboss-logmanager")
    implementation("org.jboss.logmanager:log4j2-jboss-logmanager")

    implementation(project(":lib"))
    testImplementation("io.quarkus:quarkus-junit5")
}

group = "io.gk.learning.tmp.string.kebab"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}
