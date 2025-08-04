package com.starter.app.di

import androidx.room.RoomDatabase
import com.starter.app.core.data.local.RoomSampleDataBase
import com.starter.app.core.data.local.androidDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Module for platform Android
 */
actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single<RoomDatabase.Builder<RoomSampleDataBase>> {
            androidDatabaseBuilder(androidContext())
        }
    }