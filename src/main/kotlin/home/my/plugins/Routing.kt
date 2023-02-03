package home.my.plugins

import home.my.client.StackOverflowClient
import home.my.dao.HistoryDao
import home.my.json
import home.my.model.domain.history.History
import home.my.model.dto.stackoverflow.QuestionsRequest
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.json.encodeToJsonElement
import java.time.Instant
import java.util.*

fun Application.configureRouting(historyDao: HistoryDao, stackOverflowClient: StackOverflowClient) {

    routing {
        post("/questions/without-answers") {
            val request = call.receive<QuestionsRequest>()
            val requestTime = Instant.now()

            val stackoverflowResponse = stackOverflowClient.getQuestionsWithNoAnswers(
                request.pageSize, request.order, request.sort, request.tagged
            )

            with(json) {
                val history = History(
                    UUID.randomUUID(),
                    encodeToJsonElement(request),
                    encodeToJsonElement(stackoverflowResponse),
                    requestTime
                )
                historyDao.insert(listOf(history))
            }

            call.respond(stackoverflowResponse)
        }

        post("/questions/get-by-ids") {
            val request = call.receive<QuestionsRequest>()

            val stackoverflowResponse = stackOverflowClient.getQuestionsByIds(request.ids)

            call.respond(stackoverflowResponse)
        }
    }

}
