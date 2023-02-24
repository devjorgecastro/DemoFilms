package com.devstro.demofilms.feature.filmdetail

sealed class FilmDetailIntent {
    class FetchFilmDetail(val id: Int) : FilmDetailIntent()
}
