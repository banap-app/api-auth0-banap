plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1" // Adicionando o plugin Spring Boot
    id("io.spring.dependency-management") version "1.0.15.RELEASE" // Gerenciamento de dependências do Spring
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

    // Dependências do Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Não precisa da versão, o Spring gerencia
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Dependências adicionais
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.postgresql:postgresql:42.5.0")

    implementation("com.amazonaws.serverless:aws-serverless-java-container-springboot3:2.0.1")
    implementation ("com.amazonaws:aws-lambda-java-core:1.2.1")

}

tasks.test {
    useJUnitPlatform()
}

