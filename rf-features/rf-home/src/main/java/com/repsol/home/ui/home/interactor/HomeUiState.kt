package com.repsol.home.ui.home.interactor

import com.repsol.core_platform.handler.UiState
import com.repsol.home.data.movie.remote.dto.response.MovieResponse

data class HomeUiState(
    val movies: List<MovieResponse> = emptyList(),
): UiState