package com.devstro.demofilms.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstro.demofilms.core.domain.GetFilmsNowPlayingUseCase
import com.devstro.demofilms.core.domain.GetPopularFilmsUseCase
import com.devstro.demofilms.core.domain.GetUpcomingFilmsUseCase
import com.devstro.demofilms.core.ui.UiState
import com.devstro.films.core.domainCommon.entity.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class UpcomingUiState(
    val films: List<Film> = emptyList(),
    val isLoading: Boolean = false
) : UiState

data class FilmsNowPlayingUiState(
    val films: List<Film> = emptyList(),
    val isLoading: Boolean = false
) : UiState

data class PopularFilmsUiState(
    val films: List<Film> = emptyList(),
    val isLoading: Boolean = false
) : UiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUpcomingFilms: GetUpcomingFilmsUseCase,
    getFilmsNowPlaying: GetFilmsNowPlayingUseCase,
    getPopularFilms: GetPopularFilmsUseCase
) : ViewModel() {

    private val upcomingDelegate = UpcomingFilmsDelegate(
        UpcomingUiState(isLoading = true),
        viewModelScope,
        getUpcomingFilms
    )

    private val filmsNowPlayingDelegate = FilmsNowPlayingDelegate(
        FilmsNowPlayingUiState(isLoading = true),
        viewModelScope,
        getFilmsNowPlaying
    )

    private val popularFilmsDelegate = PopularFilmsDelegate(
        PopularFilmsUiState(isLoading = true),
        viewModelScope,
        getPopularFilms
    )

    val upcomingFilmState get() = upcomingDelegate.getState()
    val filmsNowPlayingState get() = filmsNowPlayingDelegate.getState()
    val popularFilmsState get() = popularFilmsDelegate.getState()

    fun dispatchIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.FetchUpcomingFilms -> upcomingDelegate.dispatchIntent(intent)
            is HomeIntent.FetchFilmsNowPlaying -> filmsNowPlayingDelegate.dispatchIntent(intent)
            is HomeIntent.FetchPopularFilms -> popularFilmsDelegate.dispatchIntent(intent)
        }
    }
}
