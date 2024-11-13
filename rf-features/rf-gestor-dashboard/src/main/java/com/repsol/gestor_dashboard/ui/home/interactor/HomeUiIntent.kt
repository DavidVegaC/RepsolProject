package com.repsol.gestor_dashboard.ui.home.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class HomeUiIntent: UiIntent {
    data class UpdateContent(
        val content: BottomBarContent,
        val drawer: DrawerOnlyContent? = null,
    ) : HomeUiIntent()
}