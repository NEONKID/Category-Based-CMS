plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))

    implementation("io.jsonwebtoken:jjwt:0.9.1")
}