package home.my.model.dto.stackoverflow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StackoverflowResponse(
    val items: Collection<Item>,
    @SerialName("has_more") val hasMore: Boolean,
    @SerialName("quota_max") val quotaMax: Int,
    @SerialName("quota_remaining") val quotaRemaining: Int
)
