package com.repsol.gestor_dashboard.ui.index

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiState as UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IndexManagerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    override suspend fun handleIntent(intent: UiIntent) {

    }
}