package com.repsol.auth.ui.onboarding

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiEvent as UiEvent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent as UiIntent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiState as UiState

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    override suspend fun onInit() {
        UiIntent.ValidateDisplay.exec()
    }

    override suspend fun handleIntent(intent: UiIntent) {
        when(intent) {
            is UiIntent.ValidateDisplay -> validateDisplayOnboarding()
        }
    }

    private fun validateDisplayOnboarding() {
        UiEvent.GoToLogin.send()
    }
}