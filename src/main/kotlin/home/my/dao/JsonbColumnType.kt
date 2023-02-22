package home.my.dao

import com.google.gson.JsonElement
import home.my.gson
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

    override fun valueFromDB(value: Any): JsonElement = gson.toJsonTree(value)

    override fun notNullValueToDB(value: Any): String = gson.toJson(value)

}

fun Table.jsonb(name: String): Column<JsonElement> = registerColumn(name, JsonbColumnType())