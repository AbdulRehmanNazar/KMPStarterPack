package com.starter.app.di

import com.starter.app.core.data.local.DataBaseDriver
import com.starter.app.core.data.local.DatabaseDriverFactory
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
        single<DatabaseDriverFactory> {
            DataBaseDriver(androidContext())
        }
    }