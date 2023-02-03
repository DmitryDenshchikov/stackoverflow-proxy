package home.my.model.domain.history

import home.my.dao.jsonb
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

@Serializable
data class History(val request: JsonElement, val response: JsonElement, val time: Long)

object HistoryTable : Table() {
    val request = jsonb("request")
    val response = jsonb("response")
    val time = timestamp("time")

    override val primaryKey = PrimaryKey(request, response, time)
}