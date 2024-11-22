package com.repsol.gestor_dashboard.ui.index.interactor

import com.repsol.core_platform.handler.UiEffect

sealed class IndexUiEffect: UiEffect {
    data class SuccessDownloadSnackbar(val text: String): IndexUiEffect()
    data class ErrorDownloadSnackbar(val text: String): IndexUiEffect()
}