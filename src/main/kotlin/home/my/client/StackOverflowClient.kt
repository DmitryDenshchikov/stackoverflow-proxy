package home.my.client

import home.my.model.Order
import home.my.model.dto.stackoverflow.StackoverflowResponse
import io.ktor.client.*

interface StackOverflowClient : Client {

    val client: HttpClient

    suspend fun getQuestionsWithNoAnswers(
        pageSize: Int, order: Order, sort: String, tagged: Collection<String>
    ): StackoverflowResponse

}

