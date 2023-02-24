package com.devstro.demofilms.core.domain

import com.devstro.demofilms.core.data.FilmRepository
import com.devstro.films.core.domainCommon.entity.FilmResponse
import javax.inject.Inject

class GetUpcomingFilmsUseCase @Inject constructor(val filmRepository: FilmRepository) {
    suspend operator fun invoke(): FilmResponse {
        return filmRepository.getUpcomingFilms()
    }
}
