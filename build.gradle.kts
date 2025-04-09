plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("java")
}

group = "auth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":Domain"))
    implementation(project(":Infrastructure"))
    implementation(project(":Application"))
    implementation("org.postgresql:postgresql:42.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("com.auth.SampleApplication")
    archiveBaseName.set("api-auth0-banap")
}


tasks.test {
    useJUnitPlatform()
}
