package home.my.model.dto.stackoverflow

import com.google.gson.annotations.SerializedName

enum class UserType {

    @SerializedName("unregistered")
    UNREGISTERED,

    @SerializedName("registered")
    REGISTERED,

    @SerializedName("moderator")
    MODERATOR,

    @SerializedName("team_admin")
    TEAM_ADMIN,

    @SerializedName("does_not_exist")
    DOES_NOT_EXIST;

}