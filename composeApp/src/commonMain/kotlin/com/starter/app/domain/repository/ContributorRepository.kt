package com.starter.app.domain.repository

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.domain.model.Contributor

/**
 * Abstraction for the Contributor Repository
 */
interface ContributorRepository {
    suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote>

    suspend fun getLocalContributors(): Result<List<Contributor>, DataError.Local>
}