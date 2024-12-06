package com.repsol.gestor_dashboard.ui.cards.detail

import android.util.Base64
import androidx.lifecycle.SavedStateHandle
import com.repsol.core_data.common.utils.JSON
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.gestor_dashboard.domain.result.PostVehicleResult
import com.repsol.gestor_dashboard.domain.usecase.PostVehicleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiState as UiState

@HiltViewModel
class DetailCardViewModel @Inject constructor(
    private val postVehicleUseCase: PostVehicleUseCase,
    savedStateHandle: SavedStateHandle,
): CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        val itemCard: CardItem = savedStateHandle.get<String>("itemCard")?.let {
            val raw = Base64.decode(it, Base64.DEFAULT).let(::String)
            JSON.parse(raw)
        }!!
        UiState(
            item = itemCard
        )
    }
) {

    init {
        UiIntent.GetDetailVehicle.exec()
    }

    override suspend fun handleIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.NavigationBack -> UiEvent.NavigationToBack.send()
            is UiIntent.GetDetailVehicle -> postDetailVehicle()
        }
    }

    private suspend fun postDetailVehicle() {
        val plate = uiState.item.numberPlate
        if (plate.isEmpty()) return
        isLoading = true
        when (val result = postVehicleUseCase(plate)) {
            is PostVehicleResult.Error -> {

            }
            is PostVehicleResult.ServiceError -> {

            }
            is PostVehicleResult.Success -> {
                setUiState {
                    copy(
                        brand = result.data.brand,
                        type = result.data.type,
                        model = result.data.model,
                    )
                }
            }
        }
        isLoading = false
    }
}