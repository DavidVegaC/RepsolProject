package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisibility: Boolean = false
): UiState {

    val isButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && emailError.isNullOrEmpty() && passwordError.isNullOrEmpty()

    companion object {
        const val IS_ONBOARDED = "IS_ONBOARDED"
    }
}