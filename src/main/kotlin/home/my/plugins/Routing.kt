package home.my.plugins

import home.my.client.StackOverflowClientImpl
import home.my.dao.DatabaseFactory
import home.my.httpClient
import home.my.model.domain.history.History
import home.my.model.domain.history.Question
import home.my.model.dto.stackoverflow.QuestionsRequest
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting(databaseFactory: DatabaseFactory) {

    routing {
        post("/test") {
            val request = call.receive<QuestionsRequest>()

            val stackOverflowClientImpl = StackOverflowClientImpl(httpClient)
            val stackoverflowResponse = stackOverflowClientImpl.getQuestionsWithNoAnswers(
                request.pageSize, request.order, request.sort, request.tagged
            )

            stackoverflowResponse.items.asSequence().map { it.title to it.answerCount }.forEach {
                    val history = History(Question(request), it.first, it.second)
                    databaseFactory.saveHistoryRequest(history)
                }

            call.respond(stackoverflowResponse)
        }
    }

}
