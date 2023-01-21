package home.my.dao

import home.my.model.domain.history.History
import home.my.model.domain.history.HistoryTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory(jdbcUrl: String, driver: String, dbUser: String, dbPass: String) {

    private val connection: Database = Database.connect(jdbcUrl, driver, user = dbUser, password = dbPass);

    fun saveHistoryRequest(history: History) {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            val result = HistoryTable.insert {
                it[request] = history.request
                it[question] = history.question
                it[numOfAnswers] = history.numOfAnswers
            }

            println("Num of inserted rows $result")
        }
    }
}