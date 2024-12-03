package com.repsol.gestor_dashboard.ui.cards.detail.interactor

import com.repsol.core_domain.common.ActionCardModel
import com.repsol.core_domain.common.entities.CardFeature
import com.repsol.core_platform.handler.UiState
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.rf_assets.R

class DetailCardUiState(
    val item: CardItem,
): UiState {

    val isDriver: Boolean
        get() = CardFeature.isDriver(item.codeFeaturesCard)

    val actionCards: List<ActionCardModel> = listOf(
        ActionCardModel(id = 1, icon = R.drawable.ic_repeat, title = R.string.convert_to_qr),
        ActionCardModel(id = 2, icon = R.drawable.ic_repeat, title = R.string.change_status),
        ActionCardModel(id = 3, icon = R.drawable.ic_repeat, title = R.string.manage_the_card),
    )
}