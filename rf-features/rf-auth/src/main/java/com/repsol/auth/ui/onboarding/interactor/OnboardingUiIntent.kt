package com.repsol.auth.ui.onboarding.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class OnboardingUiIntent: UiIntent {
    data object ValidateDisplay: OnboardingUiIntent()
}