repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jooq:jooq-meta:3.16.2")
    implementation("org.postgresql:postgresql")
    implementation("org.testcontainers:postgresql:1.16.3")
    implementation("org.flywaydb:flyway-core")
}