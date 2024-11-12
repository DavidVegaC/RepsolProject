package com.repsol.auth.ui.onboarding

import androidx.lifecycle.SavedStateHandle
import com.repsol.auth.domain.usecase.ChangeValueOnboardedUseCase
import com.repsol.auth.domain.usecase.GetOnboardingPageUseCase
import com.repsol.core_platform.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiEvent as UiEvent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent as UiIntent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiState as UiState

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val onboardingPageUseCase: GetOnboardingPageUseCase,
    private val changeValueOnboardedUseCase: ChangeValueOnboardedUseCase,
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    init {
        UiIntent.LoadContent.exec()
    }

    override suspend fun handleIntent(intent: UiIntent) {
        when(intent) {
            UiIntent.LoadContent -> loadContent()
            UiIntent.Next -> goToNextPage()
            UiIntent.Skip -> navigateToLogin()
            UiIntent.Start -> navigateToLogin()
            is UiIntent.UpdateCurrentPage -> updatePage(intent.page)
        }
    }

    private fun updatePage(page: Int){
        setUiState { copy(currentPage = page) }
    }

    private suspend fun navigateToLogin(){
        changeValueOnboardedUseCase()
        UiEvent.GoToLogin.send()
    }

    private suspend fun loadContent() {
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