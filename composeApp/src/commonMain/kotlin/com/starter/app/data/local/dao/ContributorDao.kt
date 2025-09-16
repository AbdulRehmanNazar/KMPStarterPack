package com.starter.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.starter.app.data.local.entities.ContributorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributorDao {
    @Query("SELECT * FROM contributor")
     fun getAll(): Flow<List<ContributorEntity>>

    @Query("SELECT * FROM contributor")
    suspend fun getAllContributors(): List<ContributorEntity>

//    @Query("SELECT * FROM user where email = :email")
//    fun searchUserWithEmail(email: String): User?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertContributor(contributorEntity: ContributorEntity)

    @Update
    suspend fun updatContributor(contributorEntity: ContributorEntity)

    @Delete
    suspend fun deleteContributor(contributorEntity: ContributorEntity)

    @Query("DELETE FROM contributor")
    suspend fun clearTable()

}