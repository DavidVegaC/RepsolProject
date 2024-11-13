package com.repsol.driver_dashboard.ui.dashboard.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class HomeUiIntent: UiIntent {
    data class UpdateContent(
        val content: BottomBarContent,
    ) : HomeUiIntent()
}