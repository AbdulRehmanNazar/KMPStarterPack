package com.starter.app

import androidx.compose.ui.window.ComposeUIViewController
import com.starter.app.di.initKoin
import io.github.tbib.klocation.IOSKLocationServices
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    IOSKLocationServices().requestPermission()
    return ComposeUIViewController(configure = {
        initKoin()
    }) { App() }
}