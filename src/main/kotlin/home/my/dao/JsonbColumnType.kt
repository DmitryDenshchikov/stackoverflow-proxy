package home.my.dao

import home.my.json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject

class JsonbColumnType : ColumnType() {
    override fun sqlType(): String = "jsonb"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        super.setParameter(stmt, index, value.let {
            PGobject().apply {
                this.type = sqlType()
                this.value = it as? String
            }
        })
    }

    override fun valueFromDB(value: Any): JsonElement = json.parseToJsonElement(value.toString())

    override fun notNullValueToDB(value: Any): String = when (value) {
        is JsonElement -> json.encodeToString(JsonElement.serializer(), value)
        else -> throw IllegalArgumentException("${this.javaClass} supports only ${JsonElement::class} type)")
    }

}

fun Table.jsonb(name: String): Column<JsonElement> = registerColumn(name, JsonbColumnType())