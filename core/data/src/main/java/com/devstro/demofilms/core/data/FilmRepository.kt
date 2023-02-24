package com.devstro.demofilms.core.data

import com.devstro.demofilms.core.network.services.FilmService
import com.devstro.films.core.domainCommon.entity.FilmDetail
import com.devstro.films.core.domainCommon.entity.FilmResponse
import com.devstro.films.core.domainCommon.repository.FilmContractRepository
import javax.inject.Inject

class FilmRepository @Inject constructor(val filmService: FilmService) : FilmContractRepository {
    override suspend fun getUpcomingFilms(): FilmResponse {
        return filmService.getUpcomingFilms().mapToEntity()
    }

    override suspend fun getFilmsNowPlaying(): FilmResponse {
        return filmService.getFilmsNowPlaying().mapToEntity()
    }

    override suspend fun getPopularFilms(): FilmResponse {
        return filmService.getPopularFilms().mapToEntity()
    }

    override suspend fun getFilmDetail(id: Int): FilmDetail {
        return filmService.getFilmDetail(id).mapToEntity()
    }
}
