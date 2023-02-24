package com.devstro.demofilms.core.network.services

import com.devstro.demofilms.core.network.common.Urls
import com.devstro.demofilms.core.network.models.FilmDetail
import com.devstro.demofilms.core.network.models.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @GET(Urls.UPCOMING)
    suspend fun getUpcomingFilms(
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): FilmResponse

    @GET(Urls.PLAYING_NOW)
    suspend fun getFilmsNowPlaying(
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): FilmResponse

    @GET(Urls.POPULAR)
    suspend fun getPopularFilms(
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): FilmResponse

    @GET(Urls.FILM_DETAIL)
    suspend fun getFilmDetail(@Path("filmId") filmId: Int): FilmDetail
}
