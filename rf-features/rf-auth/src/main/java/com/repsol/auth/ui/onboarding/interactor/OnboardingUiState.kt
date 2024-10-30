package com.repsol.auth.ui.onboarding.interactor

import com.repsol.auth.data.model.OnboardingPage
import com.repsol.core_platform.handler.UiState

data class OnboardingUiState(
    val pages: List<OnboardingPage> = emptyList(),
    val currentPage: Int = INITIAL_PAGE
): UiState {

    companion object {
        const val INITIAL_PAGE = 0
    }
}

