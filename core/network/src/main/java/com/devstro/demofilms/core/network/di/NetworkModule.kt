package com.devstro.demofilms.core.network.di

import com.devstro.demofilms.core.network.RetrofitClient
import com.devstro.demofilms.core.network.common.Urls
import com.devstro.demofilms.core.network.services.FilmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideFilmService(): FilmService =
        RetrofitClient()
            .getInstance(Urls.BASE)
            .create(FilmService::class.java)
}
