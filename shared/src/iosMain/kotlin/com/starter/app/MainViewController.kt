package com.starter.app

import androidx.compose.ui.window.ComposeUIViewController
import com.starter.app.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    // initKoin() is idempotent, so it is safe even if this controller is
    // created more than once by SwiftUI.
    initKoin()
    return ComposeUIViewController { App() }
}