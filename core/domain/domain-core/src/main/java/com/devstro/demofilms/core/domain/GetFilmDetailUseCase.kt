package com.devstro.demofilms.core.domain

import com.devstro.demofilms.core.data.FilmRepository
import com.devstro.films.core.domainCommon.entity.FilmDetail
import javax.inject.Inject

class GetFilmDetailUseCase @Inject constructor(val filmRepository: FilmRepository) {
    suspend operator fun invoke(id: Int): FilmDetail {
        return filmRepository.getFilmDetail(id)
    }
}
