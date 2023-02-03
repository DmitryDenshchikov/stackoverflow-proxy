package home.my.dao

import home.my.model.domain.history.History
import home.my.model.domain.history.HistoryTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

class HistoryDao(override val connection: Database) : Dao<History> {

    override fun insert(data: Collection<History>) {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            data.forEach { history ->
                HistoryTable.insert {
                    it[request] = history.request
                    it[response] = history.response
                    it[time] = Instant.ofEpochMilli(history.time)
                }
            }
        }
    }

    override fun getAll(): Collection<History> {
        return transaction(connection) {
            HistoryTable.selectAll().asSequence().map {
                History(
                    it[HistoryTable.request], it[HistoryTable.response], it[HistoryTable.time].toEpochMilli()
                )
            }.toList();
        }
    }

}
