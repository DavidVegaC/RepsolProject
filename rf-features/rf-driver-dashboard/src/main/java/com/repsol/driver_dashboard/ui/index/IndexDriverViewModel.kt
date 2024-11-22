package com.repsol.driver_dashboard.ui.index

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.CoreViewModel
import com.repsol.driver_dashboard.domain.entity.DriverData
import com.repsol.driver_dashboard.domain.result.PostCardListResult
import com.repsol.driver_dashboard.domain.usecase.PostCardListUseCase
import com.repsol.driver_dashboard.ui.index.interactor.IndexUiIntent
import com.repsol.tools.utils.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.driver_dashboard.ui.index.interactor.IndexUiEvent as UiEvent
import com.repsol.driver_dashboard.ui.index.interactor.IndexUiIntent as UiIntent
import com.repsol.driver_dashboard.ui.index.interactor.IndexUiState as UiState

@HiltViewModel
class IndexDriverViewModel @Inject constructor(
    private val postCardListUseCase: PostCardListUseCase,
    savedStateHandle: SavedStateHandle,
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState(
            driverName = UserSession.getUserData(UserSession.NAME),
            businessName = UserSession.getUserData(UserSession.BUSINESS_NAME),
        )
    }
) {

    init {
        UiIntent.LoadInitialData.exec()
    }

    override suspend fun handleIntent(intent: UiIntent) {
        when (intent) {
            is IndexUiIntent.LoadInitialData -> loadInitialData()
        }
    }

    private suspend fun loadInitialData() {
        isLoading = true
        when (val result = postCardListUseCase()) {
            is PostCardListResult.Success -> {
                val data: DriverData = result.data
                setUiState {
                    copy(
                        cardDriver = data.driverName,
                        cardNumber = data.cardNumber,
                        cardType = data.featureDescription,
                        controlTypeDays = data.descriptionControlType,
                        stopsLimitAmount = data.maxAmount,
                        balanceAmount = data.actAmount,
                        activationDate = data.creationDate,
                        costCenter = data.descriptionCenterCost,
                    )
                }
            }
            is PostCardListResult.Error -> {
            }
        }
        isLoading = false
    }
}