package com.repsol.gestor_dashboard.ui.cards.detail

import android.util.Base64
import androidx.lifecycle.SavedStateHandle
import com.repsol.core_data.common.utils.JSON
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.domain.entity.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiState as UiState

@HiltViewModel
class DetailCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        val itemCard: CardItem = savedStateHandle.get<String>("itemCard")?.let {
            val raw = Base64.decode(it, Base64.DEFAULT).let(::String)
            JSON.parse(raw)
        }!!
        UiState(
            item = itemCard
        )
    }
) {
    override suspend fun handleIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.NavigationBack -> UiEvent.NavigationToBack.send()
        }
    }
}