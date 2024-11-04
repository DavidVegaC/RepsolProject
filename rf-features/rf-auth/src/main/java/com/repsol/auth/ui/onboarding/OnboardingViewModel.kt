package com.repsol.auth.ui.onboarding

import androidx.lifecycle.SavedStateHandle
import com.repsol.auth.domain.usecase.GetOnboardingPageUseCase
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent
import com.repsol.core_platform.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiEvent as UiEvent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent as UiIntent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiState as UiState

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val onboardingPageUseCase: GetOnboardingPageUseCase
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
            UiIntent.ValidateDisplay -> validateDisplayOnboarding()
            UiIntent.Next -> goToNextPage()
            UiIntent.Skip -> navigateToLogin()
            UiIntent.Start -> navigateToLogin()
            is OnboardingUiIntent.UpdateCurrentPage -> updatePage(intent.page)
        }
    }

    private fun updatePage(page: Int){
        setUiState { copy(currentPage = page) }
    }

    private fun navigateToLogin(){
        UiEvent.GoToLogin.send()
    }

    private suspend fun validateDisplayOnboarding() {
        // aqui se validaria si se necesita o no mostrar solo una vez (cambiar nombre de la funcion)
        setUiState {
            copy(pages = onboardingPageUseCase())
        }
    }

    private fun goToNextPage(){
        val nextPage = uiState.currentPage + 1
        if(nextPage < uiState.pages.size) {
            setUiState {
                copy(currentPage = nextPage)
            }
        }
    }
}