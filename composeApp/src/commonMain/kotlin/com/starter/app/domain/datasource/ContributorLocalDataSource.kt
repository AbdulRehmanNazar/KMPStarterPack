package com.starter.app.domain.datasource

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.domain.model.Contributor

/**
 * Abstraction for the Local DB Data Source
 */
interface ContributorLocalDataSource {
    suspend fun getContributors(): Result<List<Contributor>, DataError.Local>
    suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local>
    suspend fun clearContributors(): Result<Unit, DataError.Local>
}