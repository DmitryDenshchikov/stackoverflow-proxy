package home.my.model.dto.stackoverflow

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("account_id") val accountId: Int,
    val reputation: Int,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("user_type") val userType: UserType,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("display_name") val displayName: String,
    val link: String
)