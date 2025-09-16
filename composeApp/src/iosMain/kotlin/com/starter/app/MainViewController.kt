package com.starter.app

import androidx.compose.ui.window.ComposeUIViewController
import com.starter.app.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController(configure = {
        initKoin()
    }) { App() }
}