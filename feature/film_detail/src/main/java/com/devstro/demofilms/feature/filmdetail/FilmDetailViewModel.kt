package com.devstro.demofilms.feature.filmdetail

import androidx.lifecycle.viewModelScope
import com.devstro.demofilms.core.domain.GetFilmDetailUseCase
import com.devstro.demofilms.core.ui.BaseViewModel
import com.devstro.demofilms.core.ui.UiState
import com.devstro.films.core.domainCommon.entity.FilmDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FilmDetailState(
    val data: FilmDetail? = null,
    val isLoading: Boolean = false
) : UiState

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    val getFilmDetail: GetFilmDetailUseCase
) : BaseViewModel<FilmDetailState, FilmDetailIntent>() {
    override fun createInitialState(): FilmDetailState {
        return FilmDetailState(isLoading = true)
    }

    override fun handleIntents(intent: FilmDetailIntent) {
        when (intent) {
            is FilmDetailIntent.FetchFilmDetail -> fetchFilmDetail(intent.id)
        }
    }

    private fun fetchFilmDetail(id: Int) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val response = getFilmDetail(id)
            setState { copy(data = response, isLoading = false) }
        }
    }
}
