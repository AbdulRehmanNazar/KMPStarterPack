package com.starter.app.presentation.screens.contributor

/**
 * Intent Actions to perform in viewModel
 */
sealed interface ContributorsIntent {
    object LoadRemoteContributors : ContributorsIntent
    object LoadLocalContributors : ContributorsIntent
}