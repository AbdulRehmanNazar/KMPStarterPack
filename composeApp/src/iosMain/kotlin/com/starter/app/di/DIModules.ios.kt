package com.starter.app.di

import androidx.room.RoomDatabase
import com.starter.app.core.data.local.DataBaseDriver
import com.starter.app.core.data.local.DatabaseDriverFactory
import com.starter.app.core.data.local.RoomSampleDataBase
import com.starter.app.core.data.local.iosDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single<RoomDatabase.Builder<RoomSampleDataBase>> {
            iosDatabaseBuilder()
        }
    }