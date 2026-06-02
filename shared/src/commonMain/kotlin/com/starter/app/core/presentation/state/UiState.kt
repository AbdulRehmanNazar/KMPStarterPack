package com.starter.app.core.presentation.state

import com.starter.app.core.domain.DataError

/**
 * Generic UiState which interact with the Compose UI on the basis of different states
 */
sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: DataError) : UiState<Nothing>
}