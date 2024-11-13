package com.repsol.gestor_dashboard.ui.index.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class IndexUiEvent: UiEvent {
    data class GoToContent(val route: String): IndexUiEvent()
}