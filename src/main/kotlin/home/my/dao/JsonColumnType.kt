package home.my.dao

import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject

class JsonColumnType<T : Any>(
    private val toJson: (T) -> String, private val toObj: (String) -> T
) : ColumnType() {
    override fun sqlType(): String = "jsonb"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        super.setParameter(stmt, index, value.let {
            PGobject().apply {
                this.type = sqlType()
                this.value = it as? String
            }
        })
    }

    override fun valueFromDB(value: Any): T = toObj.invoke(value.toString())

    @Suppress("UNCHECKED_CAST")
    override fun notNullValueToDB(value: Any): String = toJson.invoke(value as T)
}