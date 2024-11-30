package com.repsol.driver_dashboard.di

import com.repsol.driver_dashboard.data.remote.data_source.DefaultIndexRemoteDS
import com.repsol.driver_dashboard.data.remote.data_source.IndexRemoteDS
import com.repsol.driver_dashboard.data.repository.IndexRepositoryImpl
import com.repsol.driver_dashboard.domain.repository.IndexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideIndexRepository(repository: IndexRepositoryImpl): IndexRepository = repository

    @Provides
    fun provideIndexRemoteDS(
        remoteDS: DefaultIndexRemoteDS
    ): IndexRemoteDS = remoteDS
}