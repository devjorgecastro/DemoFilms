package com.devstro.demofilms.core.data.di

import com.devstro.demofilms.core.data.FilmRepository
import com.devstro.films.core.domainCommon.repository.FilmContractRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindFilmRepository(
        filmRepository: FilmRepository
    ): FilmContractRepository
}
