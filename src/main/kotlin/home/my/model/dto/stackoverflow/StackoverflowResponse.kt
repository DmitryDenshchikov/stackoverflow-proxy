package home.my.model.dto.stackoverflow

import com.google.gson.annotations.SerializedName

data class StackoverflowResponse(
    val items: Collection<Item>,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("quota_max") val quotaMax: Int,
    @SerializedName("quota_remaining") val quotaRemaining: Int
)
