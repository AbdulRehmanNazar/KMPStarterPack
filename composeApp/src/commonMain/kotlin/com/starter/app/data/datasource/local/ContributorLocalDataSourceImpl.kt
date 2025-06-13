package com.starter.app.data.datasource.local

import com.starter.app.core.domain.DataError
import com.starter.app.data.local.dao.ContributorDao
import com.starter.app.data.mapper.ContributorMapper
import com.starter.app.domain.model.Contributor
import com.starter.app.core.domain.Result
import com.starter.app.domain.datasource.ContributorLocalDataSource

/**
 * Implementation of DB Entity operations with Data source
 */
class ContributorLocalDataSourceImpl(
    private val dao: ContributorDao
) : ContributorLocalDataSource {

    override suspend fun getContributors(): Result<List<Contributor>, DataError.Local> {
        return when (val result = dao.getContributors()) {
            is Result.Success -> Result.Success(result.data.map {
                ContributorMapper.entityToDomain(
                    it
                )
            })

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local> {
        contributors.forEach {
            val insertResult = dao.insertContributor(
                it.login, it.avatarUrl, it.contributions.toLong()
            )
            if (insertResult is Result.Error) return insertResult
        }
        return Result.Success(Unit)
    }

    override suspend fun clearContributors(): Result<Unit, DataError.Local> {
        return dao.deleteAll()
    }
}