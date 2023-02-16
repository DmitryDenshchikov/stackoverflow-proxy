package home.my.model.domain.history

import com.google.gson.JsonElement
import home.my.dao.jsonb
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant
import java.util.*

data class History(
    val id: UUID,
    val request: JsonElement,
    val response: JsonElement,
    val requestTime: Instant
) {
    constructor(request: Any, response: Any, requestTime: Instant, transform: Any.() -> JsonElement) : this(
        UUID.randomUUID(), request.transform(), response.transform(), requestTime
    )

}

object HistoryTable : Table() {
    val id = uuid("id")
    val request = jsonb("request")
    val response = jsonb("response")
    val requestTime = timestamp("request_time")

    override val primaryKey = PrimaryKey(id)
}