package home.my.model.domain.history

import home.my.dao.jsonb
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())

}

object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Instant", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Instant = Instant.ofEpochMilli(decoder.decodeLong())

    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeLong(value.toEpochMilli())

}

object HistoryTable : Table() {
    val id = uuid("id")
    val request = jsonb("request")
    val response = jsonb("response")
    val requestTime = timestamp("request_time")

    override val primaryKey = PrimaryKey(id)
}