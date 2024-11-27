plugins {
    id("java")
}

group = "com.auth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":Domain"))
    implementation(project(":Application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-undertow:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-mail:3.3.1")
    implementation ("org.springframework.boot:spring-boot-starter-security:3.3.1")
    //implementation (group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.6.0')
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.postgresql:postgresql:42.7.3")
}

tasks.test {
    useJUnitPlatform()
}