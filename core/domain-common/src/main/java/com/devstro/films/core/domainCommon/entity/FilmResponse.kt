package com.devstro.films.core.domainCommon.entity

data class FilmResponse(
    val dates: DateRange? = null,
    val page: Int,
    val results: List<Film>,
    val totalPages: Int,
    val totalResults: Int
)
