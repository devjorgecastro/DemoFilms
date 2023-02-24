package com.devstro.demofilms.core.network.common

import com.devstro.demofilms.core.network.BuildConfig

object Urls {
    const val BASE = "https://api.themoviedb.org/3/"
    const val UPCOMING = "movie/upcoming?api_key=${BuildConfig.API_KEY}"
    const val PLAYING_NOW = "movie/now_playing?api_key=${BuildConfig.API_KEY}"
    const val POPULAR = "movie/popular?api_key=${BuildConfig.API_KEY}"
    const val FILM_DETAIL = "movie/{filmId}?api_key=${BuildConfig.API_KEY}"
}
