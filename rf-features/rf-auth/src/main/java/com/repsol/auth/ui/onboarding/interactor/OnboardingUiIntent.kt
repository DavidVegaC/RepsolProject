package com.repsol.auth.ui.onboarding.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class OnboardingUiIntent: UiIntent {
    data object LoadContent: OnboardingUiIntent()
    data object Skip : OnboardingUiIntent()
    data object Next : OnboardingUiIntent()
    data object Start : OnboardingUiIntent()
    data class UpdateCurrentPage(val page: Int) : OnboardingUiIntent()
}