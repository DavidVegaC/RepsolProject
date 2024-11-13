package com.repsol.driver_dashboard.ui.dashboard

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import com.repsol.driver_dashboard.ui.dashboard.interactor.BottomBarContent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiEvent as UiEvent
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiIntent as UiIntent
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiState as UiState

@HiltViewModel
class DashboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    override suspend fun handleIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.UpdateContent -> updateContent(intent.content)
        }
    }

    private fun updateContent(content: BottomBarContent) {
        setUiState {
            copy(selectedContent = content)
        }
        UiEvent.GoToContent(content.route).send()
    }
}