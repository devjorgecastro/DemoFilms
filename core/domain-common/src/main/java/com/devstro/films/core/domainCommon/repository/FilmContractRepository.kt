package com.devstro.films.core.domainCommon.repository

import com.devstro.films.core.domainCommon.entity.FilmDetail
import com.devstro.films.core.domainCommon.entity.FilmResponse

interface FilmContractRepository {
    suspend fun getUpcomingFilms(): FilmResponse
    suspend fun getFilmsNowPlaying(): FilmResponse
    suspend fun getPopularFilms(): FilmResponse
    suspend fun getFilmDetail(id: Int): FilmDetail
}
