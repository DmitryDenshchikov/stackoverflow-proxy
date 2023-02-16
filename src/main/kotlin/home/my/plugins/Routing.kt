package home.my.plugins

import home.my.client.StackOverflowClient
import home.my.dao.HistoryDao
import home.my.gson
import home.my.model.domain.history.History
import home.my.model.dto.stackoverflow.QuestionsRequest
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.time.Instant

fun Application.configureRouting(historyDao: HistoryDao, stackOverflowClient: StackOverflowClient) {

    routing {
        post("/questions/without-answers") {
            val request = call.receive<QuestionsRequest>()
            val requestTime = Instant.now()

            val stackoverflowResponse = stackOverflowClient.getQuestionsWithNoAnswers(
                request.pageSize, request.order, request.sort, request.tagged
            )

            val history = History(request, stackoverflowResponse, requestTime) {
                gson.toJsonTree(this)
            }

            historyDao.insert(history)

            call.respond(stackoverflowResponse)
        }

        post("/questions/get-by-ids") {
            val request = call.receive<List<String>>()
            val requestTime = Instant.now()

            val stackoverflowResponse = stackOverflowClient.getQuestionsByIds(request)

            val history = History(request, stackoverflowResponse, requestTime) {
                gson.toJsonTree(this)
            }

            historyDao.insert(history)

            call.respond(stackoverflowResponse)
        }
    }

}
