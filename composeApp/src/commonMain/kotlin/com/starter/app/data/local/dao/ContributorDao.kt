package com.starter.app.data.local.dao

import com.starter.app.core.domain.DataError
import com.starter.app.db.AppDatabase
import com.starter.app.db.ContributorEntity
import com.starter.app.core.domain.Result

/**
 * Data Access Object class to perform CRUD operation to local DB
 */
class ContributorDao(database: AppDatabase) {
    private val queries = database.appDatabaseQueries

    fun getContributors(): Result<List<ContributorEntity>, DataError.Local> {
        return try {
            val list = queries.selectAllContributors().executeAsList()
            Result.Success(list)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
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