package com.repsol.home.ui.home

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import com.repsol.home.data.movie.DefaultMovieRepository
import com.repsol.home.ui.home.interactor.HomeUiIntent
import com.repsol.railway.onComplete
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.home.ui.home.interactor.HomeUiEvent as UiEvent
import com.repsol.home.ui.home.interactor.HomeUiIntent as UiIntent
import com.repsol.home.ui.home.interactor.HomeUiState as UiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DefaultMovieRepository
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    override suspend fun onInit() {
        UiIntent.SearchMovies("Batman").exec()
    }

    override suspend fun handleIntent(intent: HomeUiIntent) {
        when(intent) {
            is HomeUiIntent.SearchMovies -> searchMovies(intent)
        }
    }

    private suspend fun searchMovies(intent: UiIntent.SearchMovies) {
        isLoading = true
        repository.searchMovies(intent.query)
            .onComplete {
                isLoading = false
            }
            .onSuccessful {
                setUiState {
                    copy(
                        movies = it.results
                    )
                }
            }
            .onFailure {

            }
    }
}