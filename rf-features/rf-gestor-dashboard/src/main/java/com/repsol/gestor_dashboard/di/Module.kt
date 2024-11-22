package com.repsol.gestor_dashboard.di

import com.repsol.core_data.common.remote.ApiCreator
import com.repsol.gestor_dashboard.data.remote.api.CreditBalanceApi
import com.repsol.gestor_dashboard.data.remote.data_source.CreditBalanceRemoteDS
import com.repsol.gestor_dashboard.data.remote.data_source.DefaultCreditBalanceRemoteDS
import com.repsol.gestor_dashboard.data.repository.CreditBalanceRepositoryImpl
import com.repsol.gestor_dashboard.domain.repository.CreditBalanceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardModel {

    @Provides
    fun provideCreditBalanceRepository(repository: CreditBalanceRepositoryImpl): CreditBalanceRepository = repository

    @Provides
    fun provideCreditBalanceRemoteDS(
        remoteDS: DefaultCreditBalanceRemoteDS
    ): CreditBalanceRemoteDS = remoteDS

    @Singleton
    @Provides
    fun provideCreditBalanceApi(
        apiCreator : ApiCreator
    ): CreditBalanceApi = apiCreator.create()
}