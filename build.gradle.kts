import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("kapt") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.21"

    idea
}

group = "com.turnover.my"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.1")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    implementation("net.minidev:json-smart:2.4.9")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.annotation:jakarta.annotation-api")

    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

//	developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

//tasks.named("test") {
//    (this as org.gradle.api.tasks.testing.Test).useJUnitPlatform()
////    useJUnitPlatform()
//}