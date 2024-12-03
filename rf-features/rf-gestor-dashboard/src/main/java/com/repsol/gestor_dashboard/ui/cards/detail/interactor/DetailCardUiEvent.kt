package com.repsol.gestor_dashboard.ui.cards.detail.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class DetailCardUiEvent: UiEvent {
    data object NavigationToBack: DetailCardUiEvent()
}