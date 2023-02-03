package home.my.client

import home.my.model.Order
import home.my.model.dto.stackoverflow.StackOverflowProperties
import home.my.model.dto.stackoverflow.StackoverflowResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class StackOverflowClientImpl(
    private val props: StackOverflowProperties, private val client: HttpClient
) : StackOverflowClient {

    override suspend fun getQuestionsWithNoAnswers(
        pageSize: Int, order: Order, sort: String, tagged: Collection<String>
    ): StackoverflowResponse = client.get(props.host) {
        url {
            path("/2.3/questions/no-answers")
            with(parameters) {
                append("pagesize", pageSize.toString())
                append("order", order.name)
                append("sort", sort)
                append("tagged", tagged.joinToString(separator = ";"))
                append("site", props.site)
            }
        }
    }.body()

    override suspend fun getQuestionsByIds(
        ids: Collection<String>
    ): StackoverflowResponse = client.get(props.host) {
        url {
            path("/2.3/questions/no-answers" + ids.joinToString(separator = ";"))
            with(parameters) {
                append("site", props.site)
            }
        }
    }.body()

}