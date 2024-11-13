package com.repsol.driver_dashboard.ui.dashboard.interactor

import com.repsol.core_platform.handler.UiState

data class HomeUiState(
    val selectedContent: BottomBarContent = BottomBarContent.INICIO,
): UiState

enum class BottomBarContent(val route: String) {
    INICIO("inicio"),
    MY_QR("my_qr"),
    TRACKING("tracking");
}