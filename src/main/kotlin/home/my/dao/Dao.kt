package home.my.dao

import org.jetbrains.exposed.sql.Database

sealed interface Dao<T> {

    val connection: Database

    fun insert(data : Collection<T>)

    fun getAll() : Collection<T>

}