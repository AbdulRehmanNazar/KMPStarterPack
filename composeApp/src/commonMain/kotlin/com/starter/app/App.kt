package com.starter.app

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.starter.app.presentation.screens.contributor.ContributorsListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Navigator(ContributorsListScreen()) { navigator ->
        SlideTransition(navigator)
    }
}