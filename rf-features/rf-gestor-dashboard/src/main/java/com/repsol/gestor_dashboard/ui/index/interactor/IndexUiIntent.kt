package com.repsol.gestor_dashboard.ui.index.interactor

import android.content.Context
import com.repsol.core_platform.handler.UiIntent

sealed class IndexUiIntent: UiIntent {
    data class OnDownloadAllPrices(val context: Context, val isApi29OrHigher: Boolean): IndexUiIntent()
    data object OnRetryClick: IndexUiIntent()
}