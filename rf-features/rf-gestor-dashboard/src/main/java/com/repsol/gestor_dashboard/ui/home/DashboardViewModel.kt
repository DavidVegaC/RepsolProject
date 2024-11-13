package com.repsol.gestor_dashboard.ui.home

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.ui.home.interactor.BottomBarContent
import com.repsol.gestor_dashboard.ui.home.interactor.DrawerOnlyContent
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiState as UiState

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
            is UiIntent.UpdateContent -> updateContent(intent.content, intent.drawer)
        }
    }

    private fun updateContent(content: BottomBarContent, drawer: DrawerOnlyContent?) {
        setUiState {
            copy(selectedContent = content, selectedDrawerOnlyContent = drawer)
        }

        if (drawer == null) {
            UiEvent.GoToContent(content.route).send()
            return
        }
        UiEvent.GoToContent(drawer.route).send()
    }
}