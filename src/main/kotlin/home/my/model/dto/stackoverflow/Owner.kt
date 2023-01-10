package home.my.model.dto.stackoverflow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("account_id") val accountId: Int,
    val reputation: Int,
    @SerialName("user_id") val userId: Long,
    @SerialName("user_type") val userType: UserType,
    @SerialName("accept_rate") val acceptRate: Int,
    @SerialName("profile_image") val profileImage: String,
    @SerialName("display_name") val displayName: String,
    val link: String
)