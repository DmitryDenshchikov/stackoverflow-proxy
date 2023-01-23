package home.my.plugins

import home.my.client.StackOverflowClientImpl
import home.my.dao.HistoryDao
import home.my.httpClient
import home.my.model.domain.history.History
import home.my.model.domain.history.Question
import home.my.model.dto.stackoverflow.QuestionsRequest
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.json.encodeToJsonElement

fun Application.configureRouting(historyDao: HistoryDao) {

    routing {
        post("/questions/without-answers") {
            val request = call.receive<QuestionsRequest>()

            val stackOverflowClientImpl = StackOverflowClientImpl(httpClient)
            val stackoverflowResponse = stackOverflowClientImpl.getQuestionsWithNoAnswers(
                request.pageSize, request.order, request.sort, request.tagged
            )

            with(stackoverflowResponse.items.asSequence()
                .map { History(Question(request), it.title, it.answerCount) }
                .toList()) {
                historyDao.insert(this)
            }

            call.respond(stackoverflowResponse)
        }

        get("/db/rows") {
            call.respond(historyDao.getAll())
        }
    }

}
