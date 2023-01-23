package home.my.dao

import home.my.model.domain.history.History
import home.my.model.domain.history.HistoryTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class HistoryDao(override val connection: Database) : Dao<History> {

    override fun insert(data: Collection<History>) {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            data.forEach { history ->
                HistoryTable.insert {
                    it[request] = history.request
                    it[question] = history.question
                    it[numOfAnswers] = history.numOfAnswers
                }
            }
        }
    }

    override fun getAll(): Collection<History> {
        return transaction(connection) {
            HistoryTable.selectAll().asSequence().map {
                History(
                    it[HistoryTable.request], it[HistoryTable.question], it[HistoryTable.numOfAnswers]
                )
            }.toList();
        }
    }

}
