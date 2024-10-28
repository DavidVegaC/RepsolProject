package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiState

data class LoginUiState(
    val username: String = "",
    val password: String = "",
): UiState