package com.devstro.demofilms.core.data

import com.devstro.demofilms.core.network.models.BelongsToCollection
import com.devstro.demofilms.core.network.models.FilmDetail
import com.devstro.demofilms.core.network.models.Genre
import com.devstro.demofilms.core.network.models.ProductionCompany
import com.devstro.demofilms.core.network.models.ProductionCountry
import com.devstro.demofilms.core.network.models.SpokenLanguage
import com.devstro.films.core.domainCommon.entity.BelongsToCollection as BelongsToCollectionEntity
import com.devstro.films.core.domainCommon.entity.FilmDetail as FilmDetailEntity
import com.devstro.films.core.domainCommon.entity.Genre as GenreEntity
import com.devstro.films.core.domainCommon.entity.ProductionCompany as ProductionCompanyEntity
import com.devstro.films.core.domainCommon.entity.ProductionCountry as ProductionCountryEntity
import com.devstro.films.core.domainCommon.entity.SpokenLanguage as SpokenLanguageEntity

fun FilmDetail.mapToEntity() =
    FilmDetailEntity(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = this.belongsToCollection?.maptoEntity(),
        budget = this.budget,
        genres = this.genres.map { it.mapToEntity() },
        homepage = this.homepage,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { it.mapToEntity() },
        productionCountries = this.productionCountries.map { it.mapToEntity() },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.mapToEntity() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

fun BelongsToCollection.maptoEntity() =
    BelongsToCollectionEntity(
        id = this.id,
        name = this.name,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath
    )

fun Genre.mapToEntity() =
    GenreEntity(
        id = this.id,
        name = this.name
    )

fun ProductionCompany.mapToEntity() =
    ProductionCompanyEntity(
        id = this.id,
        name = this.name,
        logoPath = this.logoPath,
        originCountry = this.originCountry
    )

fun ProductionCountry.mapToEntity() =
    ProductionCountryEntity(
        iso_3166_1 = this.iso_3166_1,
        name = this.name
    )

fun SpokenLanguage.mapToEntity() =
    SpokenLanguageEntity(
        englishName = this.englishName,
        iso_639_1 = this.iso_639_1,
        name = this.name
    )
