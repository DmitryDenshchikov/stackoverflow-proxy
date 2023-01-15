package home.my

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import home.my.plugins.*
import io.ktor.client.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        configureRouting()
    }.start(wait = true)
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