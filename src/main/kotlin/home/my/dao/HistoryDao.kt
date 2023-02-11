package home.my.dao

import home.my.model.domain.history.History
import home.my.model.domain.history.HistoryTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class HistoryDao(override val connection: Database) : Dao<History> {

    override fun insert(data: History) {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            HistoryTable.insert {
                it[id] = data.id
                it[request] = data.request
                it[response] = data.response
                it[requestTime] = data.requestTime
            }
        }
    }

    override fun insert(data: Collection<History>) {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            data.forEach { history ->
                HistoryTable.insert {
                    it[id] = history.id
                    it[request] = history.request
                    it[response] = history.response
                    it[requestTime] = history.requestTime
                }
            }
        }
    }

    override fun getAll(): Collection<History> {
        return transaction(connection) {
            HistoryTable.selectAll().map {
                History(
                    it[HistoryTable.id],
                    it[HistoryTable.request],
                    it[HistoryTable.response],
                    it[HistoryTable.requestTime]
                )
            }.toList();
        }
    }

}
