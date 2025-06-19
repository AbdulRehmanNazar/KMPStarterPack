package com.starter.app.domain.repository

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.domain.model.Contributor
import kotlinx.coroutines.flow.Flow

/**
 * Abstraction for the Contributor Repository
 */
interface ContributorRepository {
    suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote>
    fun getLocalContributors(): Flow<Result<List<Contributor>, DataError.Local>>
}