package home.my.dao

import home.my.model.dto.database.DatabaseProperties
import org.jetbrains.exposed.sql.*

class DatabaseFactory(props: DatabaseProperties) {
    val connection: Database =
        Database.connect(props.jdbcUrl, props.driver, user = props.username, password = props.password)
}
