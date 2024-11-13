package com.repsol.gestor_dashboard.ui.home.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class HomeUiEvent: UiEvent {
    data class GoToContent(val route: String): HomeUiEvent()
}