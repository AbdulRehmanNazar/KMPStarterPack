package com.starter.app.domain.datasource

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.data.remote.dto.ContributorDto

/**
 * Abstraction for the Remote Data Source of Contributer
 */
interface ContributorsRemoteDataSource {
    suspend fun fetchContributors(): Result<List<ContributorDto>, DataError.Remote>
}