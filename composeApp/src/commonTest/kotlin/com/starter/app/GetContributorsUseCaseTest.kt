package com.starter.app

import com.starter.app.core.domain.DataError
import com.starter.app.domain.model.Contributor
import com.starter.app.domain.repository.ContributorRepository
import com.starter.app.domain.usecase.GetRemoteContributorsUseCase
import kotlin.test.*
import kotlinx.coroutines.test.runTest
import com.starter.app.core.domain.Result

class GetContributorsUseCaseTest {

    private lateinit var repository: FakeContributorRepository
    private lateinit var useCase: GetRemoteContributorsUseCase

    @BeforeTest
    fun setup() {
        repository = FakeContributorRepository()
        useCase = GetRemoteContributorsUseCase(repository)
    }

    @Test
    fun testUseCaseReturnsContributors() = runTest {
        val result = useCase.invoke()

        assertTrue(result is Result.Success)
        val contributors = (result as Result.Success).data
        assertEquals(2, contributors.size)
        assertEquals("user1", contributors[0].login)
    }
}

// Fake Repository
class FakeContributorRepository : ContributorRepository {
    override suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote> {
        return Result.Success(
            listOf(
                Contributor("user1", "url1", 10),
                Contributor("user2", "url2", 20)
            )
        )
    }

    override suspend fun getLocalContributors(): Result<List<Contributor>, DataError.Local> {
        return Result.Success(
            listOf(
                Contributor("user1", "url1", 10),
                Contributor("user2", "url2", 20)
            )
        )
    }

}