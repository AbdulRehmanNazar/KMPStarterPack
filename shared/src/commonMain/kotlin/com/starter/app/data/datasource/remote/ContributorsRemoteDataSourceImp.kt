package com.starter.app.data.datasource.remote

import com.starter.app.core.data.remote.apiUrl
import com.starter.app.core.data.remote.safeCall
import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.data.config.NetworkConfig
import com.starter.app.data.remote.api.ApiEndpoints
import com.starter.app.data.remote.dto.ContributorDto
import com.starter.app.domain.datasource.ContributorsRemoteDataSource
import io.ktor.client.*
import io.ktor.client.request.*

/**
 * Implementation for the Remote Data Source of Contributor
 */
class ContributorsRemoteDataSourceImp(
    private val client: HttpClient,
    private val networkConfig: NetworkConfig
) : ContributorsRemoteDataSource {
    override suspend fun fetchContributors(): Result<List<ContributorDto>, DataError.Remote> {
        return safeCall {
            client.get {
                apiUrl(networkConfig.baseUrl, ApiEndpoints.GET_CONTRIBUTORS)
            }
        }
    }
}