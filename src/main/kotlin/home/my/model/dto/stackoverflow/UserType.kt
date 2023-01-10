package home.my.model.dto.stackoverflow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UserType {

    @SerialName("unregistered")
    UNREGISTERED,

    @SerialName("registered")
    REGISTERED,

    @SerialName("moderator")
    MODERATOR,

    @SerialName("team_admin")
    TEAM_ADMIN,

    @SerialName("does_not_exist")
    DOES_NOT_EXIST;

}