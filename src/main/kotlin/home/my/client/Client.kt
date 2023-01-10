package home.my.client

import io.ktor.client.*

interface Client {

    val client: HttpClient

}