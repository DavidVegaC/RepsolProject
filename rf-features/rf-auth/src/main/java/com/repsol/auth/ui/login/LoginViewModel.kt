package com.repsol.auth.ui.login

import androidx.lifecycle.SavedStateHandle
import com.repsol.auth.domain.parameters.ValidateLoginParams
import com.repsol.auth.domain.result.ValidateLoginResult
import com.repsol.auth.domain.usecase.ValidateLoginUseCase
import com.repsol.auth.ui.login.interactor.LoginUiIntent
import com.repsol.core_platform.CoreViewModel
import com.repsol.tools.utils.ValidationUtils
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
            UiIntent.OnLoginClick -> onLoginClick()
            is LoginUiIntent.OnEmailChanged -> updateEmail(intent.email)
            is LoginUiIntent.OnPasswordChanged -> updatePassword(intent.password)
            LoginUiIntent.OnForgotPasswordClick -> onForgotPassword()
            LoginUiIntent.OnClearEmailClick -> clearEmail()
            LoginUiIntent.OnClearPasswordClick -> clearPassword()
            LoginUiIntent.OnTogglePasswordVisibility -> toggleVisibility()
        }
    }

    private suspend fun onLoginClick() {
        isLoading = true
        val validate = ValidateLoginParams(uiState.email, uiState.password)
        when (validateLoginUseCase(validate)) {
            is ValidateLoginResult.Success -> {
                UiEvent.GoToDriverDashboard.send()
            }
            is ValidateLoginResult.Error -> {
            }
            ValidateLoginResult.ErrorDefault -> {
            }
        }
        isLoading = false
    }

    private fun onForgotPassword(){
        //logica para redirigir a recuperar contraseña
    }

    private fun toggleVisibility(){
        setUiState {
            copy(isPasswordVisibility = !isPasswordVisibility)
        }
    }

    private fun clearEmail(){
        setUiState {
            copy(email = "", emailError = null)
        }
    }

    private fun clearPassword(){
        setUiState {
            copy(password = "", passwordError = null)
        }
    }

    private fun updateEmail(email: String) {
        if(ValidationUtils.isValidEmail(email)){
            setUiState {
                copy(email = email, emailError = null)
            }
        }else{
            setUiState {
                copy(email = email, emailError = "Formato de correo Inválido")
            }
        }
    }

    private fun updatePassword(password: String) {
        setUiState {
            copy(password = password, passwordError = null)
        }
    }

}