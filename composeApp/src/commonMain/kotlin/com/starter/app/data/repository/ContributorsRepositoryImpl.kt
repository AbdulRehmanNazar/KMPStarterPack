package com.starter.app.data.repository

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.domain.datasource.ContributorLocalDataSource
import com.starter.app.data.mapper.ContributorMapper
import com.starter.app.domain.datasource.ContributorsRemoteDataSource
import com.starter.app.domain.model.Contributor
import com.starter.app.domain.repository.ContributorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of Repository with Local and Remote Datasource's
 */
class ContributorRepositoryImp(
    private val contributorsRemoteDataSource: ContributorsRemoteDataSource,
    private val contributorLocalDataSource: ContributorLocalDataSource
) : ContributorRepository {

    override suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote> {
        return when (val result = contributorsRemoteDataSource.fetchContributors()) {
            is Result.Success -> {
                //Save in local DB while fetched data from server
                contributorLocalDataSource.insertContributors(result.data.map {
                    ContributorMapper.dtoToDomain(it)
                })
                Result.Success(result.data.map { ContributorMapper.dtoToDomain(it) })
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun getLocalContributors(): Result<List<Contributor>, DataError.Local> {
        return contributorLocalDataSource.getContributors()
    }
}
