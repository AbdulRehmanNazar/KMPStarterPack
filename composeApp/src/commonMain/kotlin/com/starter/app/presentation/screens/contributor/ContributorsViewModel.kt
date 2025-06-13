package com.starter.app.presentation.screens.contributor

import androidx.lifecycle.ViewModel
import com.starter.app.core.domain.onError
import com.starter.app.core.domain.onSuccess
import com.starter.app.domain.model.Contributor
import com.starter.app.core.presentation.state.UiState
import com.starter.app.domain.usecase.GetLocalContributorsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.starter.app.core.domain.Result
import com.starter.app.domain.usecase.GetRemoteContributorsUseCase

/**
 * View model for contributor to define business logic for the list of contributors
 */
class ContributorsViewModel(
    private val getRemoteContributorsUseCase: GetRemoteContributorsUseCase,
    private val getContributorsUseCase: GetLocalContributorsUseCase
) : ViewModel(){
    private val _state = MutableStateFlow<UiState<List<Contributor>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Contributor>>> = _state

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    /**
     * Load remote contributors
     */
    fun loadRemoteContributors() {
        viewModelScope.launch {
            getRemoteContributorsUseCase.invoke()
                .onSuccess { contributorList ->
                    _state.value = UiState.Success(contributorList)
                }
                .onError { error ->
                    _state.value = UiState.Error(error)
                }
        }
    }

    /**
     * Load local contributors
     */
    fun getLocalContributors() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            when (val result = getContributorsUseCase.invoke()) {
                is Result.Success -> {
                    _state.value = UiState.Success(result.data)
                }

                is Result.Error -> {
                    _state.value = UiState.Error(result.error)
                }
            }
        }
    }
}