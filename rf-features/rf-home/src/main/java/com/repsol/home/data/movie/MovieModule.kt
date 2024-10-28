package com.repsol.home.data.movie

import com.repsol.core_data.common.remote.ApiCreator
import com.repsol.home.data.movie.remote.MovieService
import com.repsol.home.data.movie.remote.data_source.DefaultMovieRemoteDS
import com.repsol.home.data.movie.remote.data_source.MovieRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MovieModule {

    @Provides
    fun provideMovieRemoteDS(
        remoteDS: DefaultMovieRemoteDS
    ): MovieRemoteDS = remoteDS

    @Provides
    fun provideMovieService(
        apiCreator: ApiCreator
    ): MovieService = apiCreator.create()
}