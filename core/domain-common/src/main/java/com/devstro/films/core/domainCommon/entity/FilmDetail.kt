package com.devstro.films.core.domainCommon.entity

data class FilmDetail(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String? = null,
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String? = null,
    val imdbId: String? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String? = null,
    val popularity: Double,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int? = null,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String? = null,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class BelongsToCollection(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val englishName: String,
    val iso_639_1: String,
    val name: String
)
