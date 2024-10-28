package com.repsol.auth.ui.login.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class LoginUiEvent: UiEvent {
    data object GoToHome: LoginUiEvent()
}