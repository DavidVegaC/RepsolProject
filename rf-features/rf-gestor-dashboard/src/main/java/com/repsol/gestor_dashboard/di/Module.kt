package com.repsol.gestor_dashboard.di

import com.repsol.core_data.common.remote.ApiCreator
import com.repsol.gestor_dashboard.data.remote.api.CardsApi
import com.repsol.gestor_dashboard.data.remote.api.IndexApi
import com.repsol.gestor_dashboard.data.remote.data_source.DefaultIndexRemoteDS
import com.repsol.gestor_dashboard.data.remote.data_source.IndexRemoteDS
import com.repsol.gestor_dashboard.data.remote.data_source.cards.CardsRemoteDs
import com.repsol.gestor_dashboard.data.remote.data_source.cards.DefaultCardsRemoteDS
import com.repsol.gestor_dashboard.data.repository.CardsRepositoryImpl
import com.repsol.gestor_dashboard.data.repository.IndexRepositoryImpl
import com.repsol.gestor_dashboard.domain.repository.CardsRepository
import com.repsol.gestor_dashboard.domain.repository.IndexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    //Index
    @Provides
    fun provideIndexRepository(repository: IndexRepositoryImpl): IndexRepository = repository

    @Provides
    fun provideIndexRemoteDS(
        remoteDS: DefaultIndexRemoteDS
    ): IndexRemoteDS = remoteDS

    @Singleton
    @Provides
    fun provideIndexApi(
        apiCreator: ApiCreator,
    ): IndexApi = apiCreator.create()

    //Cards
    @Singleton
    @Provides
    fun provideCardsApi(
        apiCreator: ApiCreator,
    ): CardsApi = apiCreator.create()

    @Provides
    fun provideCardsRemoteDS(
        remoteDS: DefaultCardsRemoteDS,
    ): CardsRemoteDs = remoteDS

    @Provides
    fun provideCardsRepository(
        repository: CardsRepositoryImpl,
    ): CardsRepository = repository
}