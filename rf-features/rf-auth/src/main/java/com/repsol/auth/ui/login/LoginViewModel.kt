package com.repsol.auth.ui.login

import androidx.lifecycle.SavedStateHandle
import com.repsol.auth.domain.parameters.ValidateLoginParams
import com.repsol.auth.domain.result.ValidateLoginResult
import com.repsol.auth.domain.usecase.ValidateLoginUseCase
import com.repsol.core_platform.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.auth.ui.login.interactor.LoginUiEvent as UiEvent
import com.repsol.auth.ui.login.interactor.LoginUiIntent as UiIntent
import com.repsol.auth.ui.login.interactor.LoginUiState as UiState

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val validateLoginUseCase: ValidateLoginUseCase,
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
        val validate = ValidateLoginParams(uiState.email, uiState.password)
        when (validateLoginUseCase(validate)) {
            is ValidateLoginResult.Success -> {
                UiEvent.GoToHome.send()
            }
            is ValidateLoginResult.Error -> {
            }
            ValidateLoginResult.ErrorDefault -> {
            }
        }
        isLoading = false
    }
}