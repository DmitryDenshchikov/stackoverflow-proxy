package home.my.client

import home.my.model.Order
import home.my.model.dto.stackoverflow.StackoverflowResponse

interface StackOverflowClient : Client {

    suspend fun getQuestionsWithNoAnswers(
        quantity: Int, order: Order, sort: String, tagged: Collection<String>
    ): StackoverflowResponse

    suspend fun getQuestionsByIds(
        ids: Collection<String>
    ): StackoverflowResponse

}

