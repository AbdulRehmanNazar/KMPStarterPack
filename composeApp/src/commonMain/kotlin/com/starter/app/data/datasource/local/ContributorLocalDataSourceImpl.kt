package com.starter.app.data.datasource.local

import com.starter.app.core.data.local.RoomSampleDataBase
import com.starter.app.core.domain.DataError
import com.starter.app.data.mapper.ContributorMapper
import com.starter.app.domain.model.Contributor
import com.starter.app.core.domain.Result
import com.starter.app.data.local.entities.ContributorEntity
import com.starter.app.data.remote.dto.ContributorDto
import com.starter.app.domain.datasource.ContributorLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of DB Entity operations with Data source
 */
class ContributorLocalDataSourceImpl(
    private val dataBase: RoomSampleDataBase
) : ContributorLocalDataSource {

    override fun getContributors(): Result<List<Contributor>, DataError.Local> {
        return try {
            val entities = dataBase.getContributorDao().getAllContributors()
            val domainList = entities.map { ContributorMapper.entityToDomain(it) }
            Result.Success(domainList)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN) // or map specific exceptions
        }
    }

    override suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local> {
        contributors.forEach {
            val insertResult = dataBase.getContributorDao().insertContributor(
                ContributorEntity(it.login, it.avatarUrl, it.contributions)
            )
        }
        return Result.Success(Unit)
    }

    override suspend fun clearContributors(): Result<Unit, DataError.Local> {
        dataBase.getContributorDao().clearTable()
        return Result.Success(Unit)
    }
}