package home.my.dao

import home.my.model.domain.history.HistoryTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(jdbcUrl: String, driver: String, dbUser: String, dbPass: String) {
        val database = Database.connect(jdbcUrl, driver, user = dbUser, password = dbPass)
        transaction(database) {
            val selectAll = HistoryTable.selectAll()
                .forEach {
                   println(it[HistoryTable.request])
                }
        }
    }
}