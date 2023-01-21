package home.my.model.domain.history

import home.my.model.Order
import home.my.model.dto.stackoverflow.QuestionsRequest
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val pageSize: Int, val order: Order, val sort: String, val tagged: Collection<String>
) {
    constructor(req: QuestionsRequest) : this(req.pageSize, req.order, req.sort, req.tagged)
}
