package com.repsol.gestor_dashboard.ui.cards.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class CardsUiIntent: UiIntent {
    data object LoadKpi: CardsUiIntent()
    data object LoadInitialCards: CardsUiIntent()
    data class UpdateSearchText(val newValue: String): CardsUiIntent()
    data class AddSelectedOption(val option: String): CardsUiIntent()
    data class RemoveSelectedOption(val option: String): CardsUiIntent()
    data object LoadPreviousPaginate: CardsUiIntent()
    data object LoadNextPaginate: CardsUiIntent()
}