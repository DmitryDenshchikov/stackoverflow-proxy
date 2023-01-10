package home.my.plugins

import home.my.client.StackOverflowClientImpl
import home.my.model.Order
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {

    routing {
        get("/test") {
            val stackOverflowClientImpl = StackOverflowClientImpl()
            val stackoverflowResponse =
                stackOverflowClientImpl.getQuestionsWithNoAnswers(2, Order.ASC, "activity", listOf("java", "spring"))
            call.respond(stackoverflowResponse)
        }
    }

}
