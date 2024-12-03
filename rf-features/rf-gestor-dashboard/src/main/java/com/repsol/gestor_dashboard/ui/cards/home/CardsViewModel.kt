package com.repsol.gestor_dashboard.ui.cards.home

import android.util.Base64
import androidx.lifecycle.SavedStateHandle
import com.repsol.core_data.common.utils.JSON
import com.repsol.core_domain.common.entities.ServiceStatus
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.gestor_dashboard.domain.result.GetKpiResult
import com.repsol.gestor_dashboard.domain.result.PostCardListResult
import com.repsol.gestor_dashboard.domain.usecase.GetKpiUseCase
import com.repsol.gestor_dashboard.domain.usecase.PostCardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.gestor_dashboard.ui.cards.home.interactor.CardsUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.cards.home.interactor.CardsUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.cards.home.interactor.CardsUiState as UiState

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getKpiUseCase: GetKpiUseCase,
    private val postCardListUseCase: PostCardListUseCase,
    savedStateHandle: SavedStateHandle,
) : CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    init {
        UiIntent.LoadKpi.exec()
        UiIntent.LoadInitialCards.exec()
    }

    override suspend fun handleIntent(intent: UiIntent) {
        when(intent){
            is UiIntent.LoadKpi -> loadKpi()
            is UiIntent.LoadInitialCards -> {
                setUiState {
                    copy(pageNumber = (pageNumber + 1))
                }
                loadNextCards()
            }
            is UiIntent.UpdateSearchText -> setUiState { copy(searchText = intent.newValue) }
            is UiIntent.LoadCardsBySearch -> {
                setUiState{
                    copy(
                        pageNumber = 1,
                        plate = searchText,
                    )
                }
                loadNextCards()
            }
            is UiIntent.AddSelectedOption -> setUiState {
                copy(selectedOptions = selectedOptions.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedOption -> setUiState {
                copy(selectedOptions = selectedOptions.filter { it != intent.option })
            }
            is UiIntent.LoadPreviousPaginate -> {
                setUiState {
                    copy(pageNumber = (pageNumber -1))
                }
                loadPreviousCards()
            }
            is UiIntent.LoadNextPaginate -> {
                setUiState {
                    copy(pageNumber = (pageNumber +1))
                }
                loadNextCards()
            }
            is UiIntent.GoToDetail -> {
                val cardItemString: String = JSON.stringify(intent.item)
                val cardItemBase64 = Base64.encodeToString(cardItemString.toByteArray(), Base64.DEFAULT)
                navigation("cards_detail/$cardItemBase64")
            }
        }
    }

    private suspend fun navigation(route: String) {
        UiEvent.GoToDetail(route).send()
    }

    private suspend fun loadKpi() {
        setUiState {
            copy(
                kpiStatus = ServiceStatus.LOADING,
            )
        }
        when (val result = getKpiUseCase()) {
            is GetKpiResult.ServiceError -> {
                setUiState {
                    copy(
                        kpiStatus = ServiceStatus.ERROR,
                    )
                }
            }
            is GetKpiResult.Error -> {
                setUiState {
                    copy(
                        kpiStatus = ServiceStatus.ERROR,
                    )
                }
            }
            is GetKpiResult.Success -> {
                val data: KpiData = result.data
                setUiState {
                    copy(
                        active = data.countActive,
                        inactive = data.countInactive,
                        canceled = data.countCanceled,
                        kpiStatus = ServiceStatus.SUCCESS,
                    )
                }
            }
        }
    }

    private suspend fun loadPreviousCards() {
        isLoading = true
        val request = uiState.requestForCardList
        when (val result = postCardListUseCase(request)) {
            is PostCardListResult.ServiceError -> {
                setUiState { copy(pageNumber = pageNumber + 1) }
            }
            is PostCardListResult.Error -> {
                setUiState { copy(pageNumber = pageNumber + 1) }
            }
            is PostCardListResult.Success -> {
                val pagination = result.data.pagination
                setUiState {
                    copy(
                        cards = result.data.items,
                        pageNumber = pagination.currentPage,
                        currentPage = pagination.currentPage,
                        totalRows = pagination.totalRows,
                        pageSize = pagination.pageSize,
                        totalPage = pagination.totalPage,
                    )
                }
            }
        }
        isLoading = false
    }

    private suspend fun loadNextCards() {
        isLoading = true
        val request = uiState.requestForCardList
        when (val result = postCardListUseCase(request)) {
            is PostCardListResult.ServiceError -> {
                setUiState { copy(pageNumber = pageNumber - 1) }
            }
            is PostCardListResult.Error -> {
                setUiState { copy(pageNumber = pageNumber - 1) }
            }
            is PostCardListResult.Success -> {
                val pagination = result.data.pagination
                setUiState {
                    copy(
                        cards = result.data.items,
                        pageNumber = pagination.currentPage,
                        currentPage = pagination.currentPage,
                        totalRows = pagination.totalRows,
                        pageSize = pagination.pageSize,
                        totalPage = pagination.totalPage,
                    )
                }
            }
        }
        isLoading = false
    }
}