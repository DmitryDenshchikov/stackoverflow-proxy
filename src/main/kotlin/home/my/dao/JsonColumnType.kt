package home.my.dao

import home.my.json
import home.my.model.domain.history.Question
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject

class JsonColumnType : ColumnType() {
    override fun sqlType(): String = "jsonb"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        super.setParameter(stmt, index, value.let {
            PGobject().apply {
                this.type = sqlType()
                this.value = value as? String
            }
        })
    }

    override fun valueFromDB(value: Any): JsonElement {
        val rowVal = value.toString()
        return Json.encodeToJsonElement(String.serializer(), rowVal)
    }

    override fun notNullValueToDB(value: Any): String = json.encodeToString(value as Question)
}