package home.my.client

import home.my.model.Order
import home.my.model.dto.stackoverflow.StackoverflowResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class StackOverflowClientImpl : StackOverflowClient {

    override suspend fun getQuestionsWithNoAnswers(
        pageSize: Int,
        order: Order,
        sort: String,
        tagged: Collection<String>
    ): StackoverflowResponse {
        val result: StackoverflowResponse
        runBlocking {
            val response: HttpResponse = client.get(
                "https://api.stackexchange.com/2.3/questions/no-answers?" +
                        "pagesize=${pageSize}&" +
                        "order=${order}&" +
                        "sort=${sort}&" +
                        "tagged=${tagged.joinToString(separator = ";")}&" +
                        "site=stackoverflow"
            )

            result = response.body()
        }


        return result
    }

    override val client: HttpClient = HttpClient() {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentEncoding) {
            gzip()
        }
        install(ContentNegotiation) {
            json()
        }
    }

}