package com.starter.app.di

import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools

fun initKoin() {
    // Guard against double initialization. ComposeUIViewController's `configure`
    // block (and SwiftUI's makeUIViewController) can run more than once; calling
    // startKoin twice throws KoinApplicationAlreadyStartedException, which on
    // iOS surfaces as an uncaught Kotlin exception (white screen, no lldb break).
    if (KoinPlatformTools.defaultContext().getOrNull() == null) {
        startKoin {
            modules(platformModule, sharedModule)
        }
    }
}