package home.my.model.domain.history

import home.my.dao.JsonColumnType
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class History(val request: Json, val question: String, val numOfAnswers: Int)

object HistoryTable : Table() {
    val request = jsonb("request")
    val question = varchar("question", 128)
    val numOfAnswers = integer("numofanswers")

    override val primaryKey = PrimaryKey(request, question)
}

fun Table.jsonb(name: String): Column<String> = registerColumn(name, JsonColumnType())