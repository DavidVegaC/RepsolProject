package com.repsol.gestor_dashboard.ui.index.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class IndexUiIntent: UiIntent {
    data object onRetryClick: IndexUiIntent()
}