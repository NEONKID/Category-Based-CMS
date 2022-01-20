import nu.studer.gradle.jooq.JooqEdition
import nu.studer.gradle.jooq.JooqGenerate
import org.jooq.meta.jaxb.ForcedType

plugins {
    id("nu.studer.jooq") version "6.0.1"
    id("org.flywaydb.flyway") version "7.15.0"
    kotlin("plugin.jpa") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jooq:jooq-meta:3.15.5")
    implementation("org.postgresql:postgresql")

    jooqGenerator("org.postgresql:postgresql")
    jooqGenerator("org.jooq:jooq")
    jooqGenerator("org.jooq:jooq-meta")

    // Flyway
    implementation("org.flywaydb:flyway-core")
    testImplementation("org.flywaydb.flyway-test-extensions:flyway-spring-test:7.0.0")

    // TestContainers
    implementation("org.testcontainers:postgresql:1.16.2")
    testImplementation("org.testcontainers:junit-jupiter:1.16.2")

    implementation("org.valiktor:valiktor-core:0.12.0")
    implementation("org.valiktor:valiktor-spring:0.12.0")

    implementation("org.apache.commons:commons-lang3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

buildscript {
    configurations["classpath"].resolutionStrategy.eachDependency {
        if (requested.group == "org.jooq") useVersion("3.15.5")
    }
}

flyway {
    url = "jdbc:postgresql:13:///cms"
    user = "postgres"
    password = "postgres"
    table = "schema_versions"
    createSchemas = true
    schemas = arrayOf("flyway")
}

jooq {
    version.set("3.15.5")
    edition.set(JooqEdition.OSS)

    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql:13:///cms"
                    user = "postgres"
                    password = "postgres"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "xyz.neonkid.cms.generator.StandalonePostgresDatabase"
                        inputSchema = "public"
                        forcedTypes.addAll(
                            arrayOf(
                                ForcedType()
                                    .withName("varchar")
                                    .withIncludeExpression(".*")
                                    .withIncludeTypes("JSONB?"),
                                ForcedType()
                                    .withName("varchar")
                                    .withIncludeExpression(".*")
                                    .withIncludeTypes("INET")
                            ).toList()
                        )
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isImmutablePojos = false
                        isFluentSetters = false
                    }
                    target.apply {
                        packageName = "xyz.neonkid.cms"
                        directory = "build/generated/jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

tasks.named<JooqGenerate>("generateJooq") {
    dependsOn("flywayMigrate")
    inputs.files(fileTree("src/main/resources/db/migration"))
        .withPropertyName("migration")
        .withPathSensitivity(PathSensitivity.RELATIVE)

    allInputsDeclared.set(true)
    outputs.cacheIf { true }
}