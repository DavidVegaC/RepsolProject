package com.repsol.gestor_dashboard.ui.cards.home.interactor

import com.repsol.core_platform.handler.UiIntent
import com.repsol.gestor_dashboard.domain.entity.CardItem

sealed class CardsUiIntent: UiIntent {
    data object LoadKpi: CardsUiIntent()
    data object LoadInitialCards: CardsUiIntent()
    data class UpdateSearchText(val newValue: String): CardsUiIntent()
    data class AddSelectedOption(val option: String): CardsUiIntent()
    data class RemoveSelectedOption(val option: String): CardsUiIntent()
    data object LoadPreviousPaginate: CardsUiIntent()
    data object LoadNextPaginate: CardsUiIntent()
    data class GoToDetail(val item: CardItem): CardsUiIntent()
    data object LoadCardsBySearch: CardsUiIntent()
}