package com.devstro.demofilms.core.data

import com.devstro.demofilms.core.network.models.DateRange
import com.devstro.demofilms.core.network.models.Film
import com.devstro.demofilms.core.network.models.FilmResponse
import com.devstro.films.core.domainCommon.entity.DateRange as DateRangeEntity
import com.devstro.films.core.domainCommon.entity.Film as FilmEntity
import com.devstro.films.core.domainCommon.entity.FilmResponse as FilmResponseEntity

fun FilmResponse.mapToEntity() =
    FilmResponseEntity(
        dates = this.dates?.mapToEntity(),
        page = this.page,
        results = this.results.mapToEntity(),
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )

fun DateRange.mapToEntity() =
    DateRangeEntity(
        maximum = this.maximum,
        minimum = this.minimum
    )

fun List<Film>.mapToEntity() =
    this.map {
        FilmEntity(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
