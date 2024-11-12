package com.repsol.auth.domain.repository

import com.repsol.auth.data.model.OnboardingPage

interface OnboardingRepository {

    suspend fun getOnboardingPages(): List<OnboardingPage>

    suspend fun changeValueOnboarded()
}