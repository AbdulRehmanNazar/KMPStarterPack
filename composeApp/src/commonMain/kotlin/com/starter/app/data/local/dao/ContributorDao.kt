package com.starter.app.data.local.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.starter.app.core.domain.DataError
import com.starter.app.db.AppDatabase
import com.starter.app.db.ContributorEntity
import com.starter.app.core.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Data Access Object class to perform CRUD operation to local DB
 */
class ContributorDao(database: AppDatabase, private val dispatcher: CoroutineDispatcher) {
    private val queries = database.appDatabaseQueries

    fun getContributors(): Flow<Result<List<ContributorEntity>, DataError.Local>> {
        return queries.selectAllContributors() // This uses your SQLDelight query
            .asFlow()                          // Convert SQLDelight Query to Flow
            .mapToList(dispatcher)                       // Map the result to a List
            .map { list ->
                Result.Success(list) as Result<List<ContributorEntity>, DataError.Local>
            }
            .catch {
                emit(Result.Error(DataError.Local.UNKNOWN)) // Emit a Result.Error on exception
            }
    }

    fun insertContributor(
        login: String,
        avatarUrl: String,
        contributions: Long
    ): Result<Unit, DataError.Local> {
        return try {
            queries.insertContributor(login, avatarUrl, contributions)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }


    fun deleteAll(): Result<Unit, DataError.Local> {
        return try {
            queries.deleteAllContributors()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}