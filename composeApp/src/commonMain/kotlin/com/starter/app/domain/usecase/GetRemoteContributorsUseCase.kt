package com.starter.app.domain.usecase

import com.starter.app.core.domain.DataError
import com.starter.app.core.domain.Result
import com.starter.app.domain.repository.ContributorRepository
import com.starter.app.domain.model.Contributor

/**
 * Use case defined for the Remote functionality
 */
class GetRemoteContributorsUseCase(
    private val repository: ContributorRepository
) {
    suspend operator fun invoke(): Result<List<Contributor>, DataError> {
        return repository.getRemoteContributors()
    }
}