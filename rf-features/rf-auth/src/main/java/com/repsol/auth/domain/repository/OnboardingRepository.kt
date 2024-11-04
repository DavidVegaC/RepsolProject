package com.repsol.auth.domain.repository

import com.repsol.auth.data.model.OnboardingPage

interface OnboardingRepository {

    fun getOnboardingPages(): List<OnboardingPage>
}