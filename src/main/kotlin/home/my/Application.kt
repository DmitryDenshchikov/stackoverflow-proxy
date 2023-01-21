package home.my

import home.my.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import home.my.plugins.*
import io.ktor.client.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val dbUser = environment.config.property("ktor.database.username").getString()
    val dbPass = environment.config.property("ktor.database.password").getString()
    val jdbcUrl = environment.config.property("ktor.database.url").getString()
    val driver = environment.config.property("ktor.database.driver").getString()

    val databaseFactory = DatabaseFactory(jdbcUrl, driver, dbUser, dbPass)

    install(ContentNegotiation) {
        json()
    }

    configureRouting(databaseFactory)
}

val httpClient: HttpClient = HttpClient {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(ContentEncoding) {
        gzip()
    }
    install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
        json()
    }
}

val json = Json { encodeDefaults = true }