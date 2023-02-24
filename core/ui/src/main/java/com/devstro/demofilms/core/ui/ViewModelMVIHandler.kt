package com.devstro.demofilms.core.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

interface UiState

abstract class ViewModelMVIHandler<state : UiState, AppIntent>(
    val initialState: state,
    val viewModelScope: CoroutineScope
) {
    //private val initialState: UiState by lazy { createInitialState() }

    private val _viewState: MutableState<state> = mutableStateOf(initialState)
    val viewState: State<state> = _viewState

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

    fun setState(reducer: state.() -> state) {
        _viewState.value = viewState.value.reducer()
    }

    fun dispatchIntent(intent: AppIntent) {
        viewModelScope.launch { _event.emit(intent) }
    }
}
