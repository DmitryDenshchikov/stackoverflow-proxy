package home.my.model.dto.stackoverflow

import home.my.model.Order
import kotlinx.serialization.Serializable

@Serializable
data class QuestionsRequest(
    val pageSize: Int, val order: Order, val sort: String, val tagged: Collection<String>
)
