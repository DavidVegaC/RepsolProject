package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class LoginUiIntent: UiIntent {
    data class OnEmailChanged(val email: String) : LoginUiIntent()
    data class OnPasswordChanged(val password: String) : LoginUiIntent()
    data object OnClearEmailClick: LoginUiIntent()
    data object OnClearPasswordClick: LoginUiIntent()
    data object OnTogglePasswordVisibility: LoginUiIntent()
    data object OnLoginClick: LoginUiIntent()
    data object OnForgotPasswordClick: LoginUiIntent()
}