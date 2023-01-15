package home.my.client

import home.my.model.Order
import home.my.model.dto.stackoverflow.StackoverflowResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*

class StackOverflowClientImpl(override val client: HttpClient) : StackOverflowClient {

    override suspend fun getQuestionsWithNoAnswers(
        pageSize: Int, order: Order, sort: String, tagged: Collection<String>
    ): StackoverflowResponse {
        return client.get(
            "https://api.stackexchange.com/2.3/questions/no-answers?pagesize=${pageSize}&order=${order}&sort=${sort}&tagged=${
                tagged.joinToString(
                    separator = ";"
                )
            }&site=stackoverflow"
        ).body()
    }

}