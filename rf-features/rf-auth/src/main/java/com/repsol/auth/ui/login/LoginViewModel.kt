package com.repsol.auth.ui.login

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject
import com.repsol.auth.ui.login.interactor.LoginUiState as UiState
import com.repsol.auth.ui.login.interactor.LoginUiEvent as UiEvent
import com.repsol.auth.ui.login.interactor.LoginUiEffect as UiEffect
import com.repsol.auth.ui.login.interactor.LoginUiIntent as UiIntent

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    override suspend fun handleIntent(intent: UiIntent) {
        when(intent) {
            is UiIntent.OnLoginClick -> onLoginClick()
        }
    }

    private suspend fun onLoginClick() {
        isLoading = true
        delay(3000)
        isLoading = false
        UiEvent.GoToHome.send()
    }
}