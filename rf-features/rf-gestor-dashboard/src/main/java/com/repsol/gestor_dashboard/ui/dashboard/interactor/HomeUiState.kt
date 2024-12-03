package com.repsol.gestor_dashboard.ui.dashboard.interactor

import com.repsol.core_platform.handler.UiState

data class HomeUiState(
    val selectedContent: BottomBarContent = BottomBarContent.INICIO,
    val selectedDrawerOnlyContent: DrawerOnlyContent? = null,
): UiState

enum class BottomBarContent(val route: String) {
    INICIO("inicio"),
    VEHICULOS("vehiculos"),
    CARDS("cards"),
    CONDUCTORES("conductores"),
    NONE("")
}

enum class DrawerOnlyContent(val route: String) {// Opciones exclusivas del Drawer
    TRACKING("tracking"),
    REPORTES("reportes"),
    MEDIOS_PAGO("medios_pago"),
    CONFIGURACIONES("configuraciones")
}