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
import com.starter.app.domain.repository.ContributorRepository
import com.starter.app.domain.usecase.GetRemoteContributorsUseCase

/**
 * View model for contributor to define business logic for the list of contributors
 */
class ContributorsViewModel(
    private val getRemoteContributorsUseCase: GetRemoteContributorsUseCase,
    private val getContributorsUseCase: GetLocalContributorsUseCase,
    private val contributorRepository: ContributorRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Contributor>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Contributor>>> = _state

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    init {
        handleIntent(ContributorsIntent.LoadRemoteContributors)
    }

    /**
     * Handle input actions
     */
    fun handleIntent(intent: ContributorsIntent) {
        when (intent) {
            is ContributorsIntent.LoadRemoteContributors -> {
                loadRemoteContributors()
            }

            is ContributorsIntent.LoadLocalContributors -> {
                getLocalContributors()
            }
        }
    }

    /**
     * Load remote contributors
     */
    private fun loadRemoteContributors() {
        viewModelScope.launch {
            contributorRepository.getRemoteContributors()
                .onSuccess {
                    val current = _state.value
                    if (current !is UiState.Success || current.data.isEmpty()) {
                        // only reload local if we don’t already have data
                        getLocalContributors()
                    }
                }
                .onError {

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