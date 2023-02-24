package com.devstro.demofilms.core.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, AppIntent> : ViewModel() {

    private val initialState: UiState by lazy { createInitialState() }

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    private val _event: MutableSharedFlow<AppIntent> = MutableSharedFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleIntents(it)
            }
        }
    }

    abstract fun createInitialState(): UiState

    abstract fun handleIntents(intent: AppIntent)

    fun getState() = viewState.value

    fun setState(reducer: UiState.() -> UiState) {
        _viewState.value = viewState.value.reducer()
    }

    fun dispatchIntent(intent: AppIntent) {
        viewModelScope.launch { _event.emit(intent) }
    }
}
