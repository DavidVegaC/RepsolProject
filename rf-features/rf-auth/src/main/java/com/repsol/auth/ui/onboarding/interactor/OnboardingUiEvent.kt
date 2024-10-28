package com.repsol.auth.ui.onboarding.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class OnboardingUiEvent: UiEvent {
    data object GoToLogin: OnboardingUiEvent()
}