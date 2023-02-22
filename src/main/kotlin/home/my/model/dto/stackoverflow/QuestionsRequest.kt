package home.my.model.dto.stackoverflow

import home.my.model.Order

data class QuestionsRequest(
    val quantity: Int, val order: Order, val sort: String, val tagged: Collection<String>
)
