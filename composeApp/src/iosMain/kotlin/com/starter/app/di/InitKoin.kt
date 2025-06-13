package com.starter.app.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(platformModule,sharedModule)
    }
}