package home.my.model.domain.history

import home.my.dao.jsonb
import home.my.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import org.jetbrains.exposed.sql.Table

@Serializable
data class History(val request: Question, val question: String, val numOfAnswers: Int) {}

object HistoryTable : Table() {
    val request = jsonb("request", json::encodeToString) {
        json.decodeFromString(Question.serializer(), it)
    }
    val question = varchar("question", 128)
    val numOfAnswers = integer("numofanswers")

    override val primaryKey = PrimaryKey(request, question)
}