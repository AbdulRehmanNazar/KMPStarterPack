package com.starter.app.domain.datasource

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.data.remote.dto.ContributorDto
import com.starter.app.domain.model.Contributor
import kotlinx.coroutines.flow.Flow

/**
 * Abstraction for the Local DB Data Source
 */
interface ContributorLocalDataSource {
    fun getContributors(): Flow<Result<List<Contributor>, DataError.Local>>
    suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local>
    suspend fun clearContributors(): Result<Unit, DataError.Local>
}