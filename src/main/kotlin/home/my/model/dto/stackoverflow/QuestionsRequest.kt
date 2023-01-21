package home.my.model.dto.stackoverflow

import home.my.model.Order
import kotlinx.serialization.Serializable

@Serializable
data class QuestionsRequest(
    val pageSize: Int = 2, val order: Order = Order.ASC, val sort: String = "activity", val tagged: Collection<String>
)
