package xyz.neonkid.cms.jooq

import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL
import org.jooq.meta.postgres.PostgresDatabase
import org.jooq.tools.jdbc.JDBCUtils
import org.postgresql.Driver
import org.testcontainers.containers.PostgreSQLContainer
import java.util.*

class StandalonePostgresDatabase: PostgresDatabase() {
    private lateinit var postgresSQLContainer: PostgreSQLContainer<*>

    companion object {
        const val DEFAULT_FLYWAY_LOCATION = "filesystem:src/main/resources/db/migration"
        const val DEFAULT_DOCKER_IMAGE = "postgres:13"
    }

    override fun create0(): DSLContext {
        if (connection == null) {
            try {
                postgresSQLContainer = PostgreSQLContainer(DEFAULT_DOCKER_IMAGE)
                    .withDatabaseName("cms")
                    .withUsername("postgres")
                    .withPassword("postgres")
                postgresSQLContainer.start()

                // Create and connect the JDBC driver
                val driver = Driver()

                val properties = Properties()
                properties["user"] = postgresSQLContainer.username
                properties["password"] = postgresSQLContainer.password

                connection = driver.connect(postgresSQLContainer.jdbcUrl, properties)

                // Use the common flyway location as single input src
//                val locations: Array<String> = arrayOf(DEFAULT_FLYWAY_LOCATION)

                // Use the datasource of the test container to execute the Flyway migrations
                Flyway.configure().dataSource(postgresSQLContainer.jdbcUrl, postgresSQLContainer.username, postgresSQLContainer.password)
                    .locations(DEFAULT_FLYWAY_LOCATION).schemas("public").load().migrate()

                // Set the current connection so it can be reused
                connection = connection
            } catch (ex: Exception) {
                throw DataAccessException("Unable to start the database container and migrate the schemas: ${ex.message} $ex ")
            }
        }
        return DSL.using(connection, SQLDialect.POSTGRES)
    }

    override fun close() {
        // Close the connection properly
        JDBCUtils.safeClose(connection)
        connection = null

        super.close()   // Empty method call

        // Stop the container
        postgresSQLContainer.stop()
    }
}