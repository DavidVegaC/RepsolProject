package com.repsol.auth.di

import com.repsol.auth.data.remote.api.UserInformationApi
import com.repsol.auth.data.remote.data_source.DefaultUserInformationRemoteDS
import com.repsol.auth.data.remote.data_source.UserInformationRemoteDS
import com.repsol.auth.data.repository.LoginRepositoryImpl
import com.repsol.auth.data.repository.OnboardingRepositoryImpl
import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.auth.domain.repository.OnboardingRepository
import com.repsol.core_data.common.remote.ApiCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModel {

    @Provides
    fun provideOnboardingRepository(repository: OnboardingRepositoryImpl): OnboardingRepository = repository

    @Provides
    fun provideAuthRepository(repository: LoginRepositoryImpl): LoginRepository = repository

    @Provides
    fun provideUserInformationRemoteDS(
        remoteDS: DefaultUserInformationRemoteDS
    ): UserInformationRemoteDS = remoteDS

    @Singleton
    @Provides
    fun provideUserInformationApi(
        apiCreator: ApiCreator
    ): UserInformationApi = apiCreator.create()
}