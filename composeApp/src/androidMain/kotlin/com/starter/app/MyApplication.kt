package com.starter.app

import android.app.Application
import com.starter.app.di.platformModule
import com.starter.app.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for Android to start components like koin
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }
        startKoin {
            androidContext(this@MyApplication)
            modules(platformModule, sharedModule)
        }
    }
}