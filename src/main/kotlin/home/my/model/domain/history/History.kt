package home.my.model.domain.history

import home.my.dao.jsonb
import home.my.util.InstantSerializer
import home.my.util.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant
import java.util.*

@Serializable
data class History(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val request: JsonElement,
    val response: JsonElement,
    @Serializable(with = InstantSerializer::class) val requestTime: Instant
)

object HistoryTable : Table() {
    val id = uuid("id")
    val request = jsonb("request")
    val response = jsonb("response")
    val requestTime = timestamp("request_time")

    override val primaryKey = PrimaryKey(id)
}