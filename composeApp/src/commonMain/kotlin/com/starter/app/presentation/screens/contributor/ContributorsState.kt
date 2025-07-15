package com.starter.app.presentation.screens.contributor

import com.starter.app.core.presentation.state.UiState
import com.starter.app.domain.model.Contributor

data class ContributorsState(
    val contributorsUiState: UiState<List<Contributor>> = UiState.Idle
)