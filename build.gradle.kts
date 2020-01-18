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
    id("io.franzbecker.gradle-lombok") version "3.1.0"
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.2.3.RELEASE")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

group = "com.gorlah"
version = "0.0.1-SNAPSHOT"

description = "KappaBot: a Discord chat bot"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.github.ben-manes.caffeine:caffeine:2.8.1")
    implementation("net.dv8tion:JDA:4.1.0_100") {
        exclude("club.minnced:opus-java")
    }
    implementation("org.jsoup:jsoup:1.12.1")
    implementation("org.apache.commons:commons-text:1.8")
    implementation("com.google.guava:guava:28.2-jre")

    // Database drivers
    implementation("mysql:mysql-connector-java:8.0.19")
    implementation("com.h2database:h2:1.4.200")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.10")
    annotationProcessor("org.projectlombok:lombok:1.18.10")
}
