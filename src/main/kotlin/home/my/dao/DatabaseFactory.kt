package home.my.dao

import home.my.model.dto.database.DatabaseProperties
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.*

class DatabaseFactory(props: DatabaseProperties) {
    val connection: Database =
        Database.connect(props.jdbcUrl, props.driver, user = props.username, password = props.password)
}

fun Table.jsonb(name: String): Column<JsonElement> = registerColumn(name, JsonColumnType())