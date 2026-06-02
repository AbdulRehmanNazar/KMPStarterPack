package com.starter.app

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.starter.app.presentation.screens.contributor.ContributorsListScreen

@Composable
@Preview
fun App() {
    Navigator(ContributorsListScreen()) { navigator ->
        SlideTransition(navigator)
    }
}