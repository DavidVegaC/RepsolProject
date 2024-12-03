package com.repsol.gestor_dashboard.ui.cards.home.interactor

import com.repsol.core_platform.handler.UiEvent

sealed class CardsUiEvent: UiEvent {
    data class GoToDetail(val route: String): CardsUiEvent()
}