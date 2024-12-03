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
import com.repsol.tools.utils.EMPTY_STRING
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
            is UiIntent.UpdateSearchText -> setUiState { copy(plate = intent.newValue) }
            is UiIntent.LoadCardsBySearch -> {
                setUiState{ copy(pageNumber = 1) }
                loadNextCards()
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
            is UiIntent.GoToFilter -> {
                navigation("filter_cards")
            }

            //Intents del filter
            is UiIntent.SelectAllCardFeatureOption -> setUiState {
                copy(
                    selectedCardFeatures = cardFeatures
                )
            }
            is UiIntent.AddSelectedCardFeature -> setUiState {
                copy(selectedCardFeatures = selectedCardFeatures.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedCardFeature -> setUiState {
                copy(selectedCardFeatures = selectedCardFeatures.filter { it != intent.option })
            }
            is UiIntent.OnChangeValueForDateRange -> setUiState {
                copy(startDate = intent.startDate, endDate = intent.endDate)
            }
            is UiIntent.SelectAllCardStateOption -> setUiState {
                copy(
                    selectedCardStates = cardStates
                )
            }
            is UiIntent.AddSelectedCardState -> setUiState {
                copy(selectedCardStates = selectedCardStates.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedCardState -> setUiState {
                copy(selectedCardStates = selectedCardStates.filter { it != intent.option })
            }
            is UiIntent.UpdateDriverName -> setUiState {
                copy(driverName = intent.newValue)
            }
            is UiIntent.SelectAllDocumentTypeOption -> setUiState {
                copy(
                    selectedDocumentTypes = documentTypes
                )
            }
            is UiIntent.AddSelectedDocumentType -> setUiState {
                copy(selectedDocumentTypes = selectedDocumentTypes.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedDocumentType -> setUiState {
                copy(selectedDocumentTypes = selectedDocumentTypes.filter { it != intent.option })
            }
            is UiIntent.UpdateDocumentNumber -> setUiState {
                copy(numberDocument = intent.newValue)
            }
            is UiIntent.SelectAllAttentionTypeOption -> setUiState {
                copy(
                    selectedAttentionTypes = attentionTypes
                )
            }
            is UiIntent.AddSelectedAttentionType -> setUiState {
                copy(selectedAttentionTypes = selectedAttentionTypes.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedAttentionType -> setUiState {
                copy(selectedAttentionTypes = selectedAttentionTypes.filter { it != intent.option })
            }
            is UiIntent.SelectAllPhysicalCardStateOption -> setUiState {
                copy(
                    selectedPhysicalCardStates = physicalCardStates
                )
            }
            is UiIntent.AddSelectedPhysicalCardState -> setUiState {
                copy(selectedPhysicalCardStates = selectedPhysicalCardStates.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedPhysicalCardState -> setUiState {
                copy(selectedPhysicalCardStates = selectedPhysicalCardStates.filter { it != intent.option })
            }
            is UiIntent.SelectAllMileageStatusOption -> setUiState {
                copy(
                    selectedMileageStatus = mileageStatus
                )
            }
            is UiIntent.AddSelectedMileageStatus -> setUiState {
                copy(selectedMileageStatus = selectedMileageStatus.toMutableList().apply {
                    if (!contains(intent.option)) add(intent.option)
                })
            }
            is UiIntent.RemoveSelectedMileageStatus -> setUiState {
                copy(selectedMileageStatus = selectedMileageStatus.filter { it != intent.option })
            }
            is UiIntent.ApplyFilters -> {
                setUiState{ copy(pageNumber = 1, isLoadingFilter = true) }
                loadCardsWithFilters()
            }
            is UiIntent.CleanFilters -> cleanFilters()
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

    private suspend fun loadCardsWithFilters() {
        val request = uiState.requestForCardList
        when (val result = postCardListUseCase(request)) {
            is PostCardListResult.ServiceError -> {
                setUiState { copy(pageNumber = pageNumber - 1, isLoadingFilter = false) }
            }
            is PostCardListResult.Error -> {
                setUiState { copy(pageNumber = pageNumber - 1, isLoadingFilter = false) }
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
                        isLoadingFilter = false,
                    )
                }
            }
        }
    }

    private suspend fun cleanFilters() {
        setUiState {
            copy(
                cardNumber = EMPTY_STRING,
                listIdCenterCost = emptyList(),
                listCodeAssignmentCard = emptyList(),
                selectedCardFeatures = emptyList(),
                startDate = null,
                endDate = null,
                selectedCardStates = emptyList(),
                driverName = EMPTY_STRING,
                selectedDocumentTypes = emptyList(),
                numberDocument = EMPTY_STRING,
                selectedAttentionTypes = emptyList(),
                selectedPhysicalCardStates = emptyList(),
                selectedMileageStatus = emptyList(),
            )
        }
    }
}