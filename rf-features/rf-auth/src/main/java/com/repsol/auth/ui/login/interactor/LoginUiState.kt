package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
): UiState