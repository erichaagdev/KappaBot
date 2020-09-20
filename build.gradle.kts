buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin")
    }
}

plugins {
    java
    idea
    id("io.freefair.lombok") version "5.2.1"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.gorylenko.gradle-git-properties") version "2.2.3"
}

springBoot {
    buildInfo()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.4.RELEASE")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

group = "com.gorlah"
version = "0.0.1-SNAPSHOT"

description = "KappaBot: a flexible Java chat bot"

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

tasks {

    withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }
    
    test {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }
}

dependencies {
    implementation("jakarta.inject:jakarta.inject-api:1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.github.ben-manes.caffeine:caffeine:2.8.1")
    implementation("net.dv8tion:JDA:4.2.0_168") {
        exclude("club.minnced:opus-java")
    }
    implementation("org.jsoup:jsoup:1.12.1")
    implementation("org.apache.commons:commons-text:1.8")
    implementation("com.google.guava:guava:28.2-jre")
    implementation("com.atlassian.commonmark:commonmark:0.14.0")

    // SpringDoc & OpenAPI 3.0
    implementation("org.springdoc:springdoc-openapi-ui:1.4.6")

    // Database drivers
    implementation("mysql:mysql-connector-java:8.0.19")
    implementation("com.h2database:h2:1.4.200")

    // Test dependencies
    testImplementation("io.rest-assured:rest-assured:4.2.0")
    testImplementation("io.rest-assured:rest-assured-all:4.2.0")
    testImplementation("io.rest-assured:json-path:4.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
}
