package home.my.dao

import org.jetbrains.exposed.sql.*

class DatabaseFactory(jdbcUrl: String, driver: String, dbUser: String, dbPass: String) {
    val connection: Database = Database.connect(jdbcUrl, driver, user = dbUser, password = dbPass)
}

fun <T : Any> Table.jsonb(name: String, toJson: (T) -> String, toObj: (String) -> T): Column<T> =
    registerColumn(name, JsonColumnType(toJson, toObj))