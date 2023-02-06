package home.my.model.dto.stackoverflow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val tags: Collection<String>,
    val owner: Owner,
    @SerialName("is_answered") val isAnswered: Boolean,
    @SerialName("view_count") val viewCount: Int,
    @SerialName("answer_count")  val answerCount: Int,
    val score: Int,
    @SerialName("last_activity_date") val lastActivityDate: Long,
    @SerialName("creation_date") val creationDate: Long,
    @SerialName("question_id") val questionId: Int,
    @SerialName("content_license") val contentLicense: String,
    val link: String,
    val title: String
)