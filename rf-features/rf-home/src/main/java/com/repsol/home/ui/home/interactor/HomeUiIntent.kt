package com.repsol.home.ui.home.interactor

import com.repsol.core_platform.handler.UiIntent

sealed class HomeUiIntent: UiIntent {
    data class SearchMovies(val query: String): HomeUiIntent()
}