package home.my

import home.my.client.StackOverflowClientImpl
import home.my.dao.DatabaseFactory
import home.my.dao.HistoryDao
import home.my.model.dto.database.DatabaseProperties
import home.my.model.dto.stackoverflow.StackOverflowProperties
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

    val dbProperties = with(environment.config) {
        DatabaseProperties(
            property("ktor.database.url").getString(),
            property("ktor.database.driver").getString(),
            property("ktor.database.username").getString(),
            property("ktor.database.password").getString()
        )
    }

    val stackOverflowProperties = with(environment.config) {
        StackOverflowProperties(
            property("stack_overflow.host").getString(),
            property("stack_overflow.site").getString()
        )
    }

    val databaseFactory = DatabaseFactory(dbProperties)
    val historyDao = HistoryDao(databaseFactory.connection)
    val stackoverflowClient = StackOverflowClientImpl(stackOverflowProperties, httpClient)

    install(ContentNegotiation) {
        json()
    }

    configureRouting(historyDao, stackoverflowClient)
}

val httpClient: HttpClient = HttpClient {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
    install(ContentEncoding) {
        gzip()
    }
    install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
        json()
    }
}

val json = Json { encodeDefaults = true }