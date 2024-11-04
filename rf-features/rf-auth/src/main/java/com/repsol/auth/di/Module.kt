package com.repsol.auth.di

import com.repsol.auth.data.repository.LoginRepositoryImpl
import com.repsol.auth.data.repository.OnboardingRepositoryImpl
import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.auth.domain.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModel {

    @Provides
    fun provideOnboardingRepository(repository: OnboardingRepositoryImpl): OnboardingRepository = repository

    @Provides
    fun provideAuthRepository(repository: LoginRepositoryImpl): LoginRepository = repository
}