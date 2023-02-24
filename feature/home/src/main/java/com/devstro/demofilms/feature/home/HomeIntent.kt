package com.devstro.demofilms.feature.home

sealed class HomeIntent {
    object FetchUpcomingFilms : HomeIntent()
    object FetchFilmsNowPlaying : HomeIntent()
    object FetchPopularFilms : HomeIntent()
}
