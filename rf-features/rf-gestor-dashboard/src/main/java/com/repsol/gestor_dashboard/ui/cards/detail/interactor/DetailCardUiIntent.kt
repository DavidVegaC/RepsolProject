package com.repsol.gestor_dashboard.ui.cards.detail.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class DetailCardUiIntent: UiIntent {
    data object GetDetailVehicle: DetailCardUiIntent()
    data object NavigationBack: DetailCardUiIntent()
}