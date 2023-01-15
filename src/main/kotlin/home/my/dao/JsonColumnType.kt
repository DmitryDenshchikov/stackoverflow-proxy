package home.my.dao

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ColumnType

class JsonColumnType : ColumnType() {
    override fun sqlType(): String = "jsonb"

    override fun valueFromDB(value: Any): Any {
        val rowVal = value.toString()
        return Json.encodeToJsonElement(String.serializer(), rowVal)
    }
}