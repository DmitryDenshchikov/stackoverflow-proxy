package home.my.model.dto.stackoverflow

import com.google.gson.annotations.SerializedName


data class Item(
    val tags: Collection<String>,
    val owner: Owner,
    @SerializedName("is_answered") val isAnswered: Boolean,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("answer_count")  val answerCount: Int,
    val score: Int,
    @SerializedName("last_activity_date") val lastActivityDate: Long,
    @SerializedName("creation_date") val creationDate: Long,
    @SerializedName("question_id") val questionId: Int,
    @SerializedName("content_license") val contentLicense: String,
    val link: String,
    val title: String
)