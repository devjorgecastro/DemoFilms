package com.devstro.demofilms.feature.home

import com.devstro.demofilms.core.domain.GetFilmsNowPlayingUseCase
import com.devstro.demofilms.core.domain.GetPopularFilmsUseCase
import com.devstro.demofilms.core.domain.GetUpcomingFilmsUseCase
import com.devstro.demofilms.core.ui.ViewModelMVIHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UpcomingFilmsDelegate(
    initialState: UpcomingUiState,
    viewModelScope: CoroutineScope,
    private val getUpcomingFilms: GetUpcomingFilmsUseCase
) : ViewModelMVIHandler<UpcomingUiState, HomeIntent.FetchUpcomingFilms>(initialState, viewModelScope) {

    override fun createInitialState() = UpcomingUiState(isLoading = true)

    override fun handleIntents(intent: HomeIntent.FetchUpcomingFilms) {
        fetchData()
    }

    private fun fetchData() {
        setState { UpcomingUiState().copy(isLoading = true) }

        viewModelScope.launch {
            val response = getUpcomingFilms.invoke()
            setState { copy(films = response.results, isLoading = false) }
        }
    }
}

class FilmsNowPlayingDelegate(
    initialState: FilmsNowPlayingUiState,
    viewModelScope: CoroutineScope,
    private val getFilmsNowPlaying: GetFilmsNowPlayingUseCase
) : ViewModelMVIHandler<FilmsNowPlayingUiState, HomeIntent.FetchFilmsNowPlaying>(initialState, viewModelScope) {

    override fun createInitialState() = UpcomingUiState(isLoading = true)

    override fun handleIntents(intent: HomeIntent.FetchFilmsNowPlaying) {
        fetchData()
    }

    private fun fetchData() {
        setState { copy(isLoading = true) }

        viewModelScope.launch {
            val response = getFilmsNowPlaying.invoke()
            setState { copy(films = response.results, isLoading = false) }
        }
    }
}

class PopularFilmsDelegate(
    initialState: PopularFilmsUiState,
    viewModelScope: CoroutineScope,
    private val getPopularFilms: GetPopularFilmsUseCase
) : ViewModelMVIHandler<PopularFilmsUiState, HomeIntent.FetchPopularFilms>(initialState, viewModelScope) {

    override fun createInitialState() = PopularFilmsUiState(isLoading = true)

    override fun handleIntents(intent: HomeIntent.FetchPopularFilms) {
        fetchData()
    }

    private fun fetchData() {
        setState { copy(isLoading = true) }

        viewModelScope.launch {
            val response = getPopularFilms.invoke()
            setState { copy(films = response.results, isLoading = false) }
        }
    }
}
