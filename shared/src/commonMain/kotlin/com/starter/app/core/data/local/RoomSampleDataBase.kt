package com.starter.app.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.starter.app.data.local.dao.ContributorDao
import com.starter.app.data.local.entities.ContributorEntity

@Database(
    entities = [ContributorEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomSampleDataBase : RoomDatabase() {
    abstract fun getContributorDao(): ContributorDao

    companion object Companion {
        const val DB_NAME = "RoomSampleDataBase.db"
    }
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomSampleDataBase> {
    override fun initialize(): RoomSampleDataBase
}