package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class LoginUiIntent: UiIntent {
    data object OnLoginClick: LoginUiIntent()
}