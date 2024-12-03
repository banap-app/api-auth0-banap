rootProject.name = "auth-microservice"
include("Domain")
include("Domain")
include("Application")
include("Application")
include("Application")
include("Infrastructure")

rootProject.name = "root-project"
include ("Domain", "Application", "Infrastructure")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}