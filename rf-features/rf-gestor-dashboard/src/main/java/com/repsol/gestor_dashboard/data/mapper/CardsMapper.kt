package com.repsol.gestor_dashboard.data.mapper

import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.dto.response.CardResponse
import com.repsol.core_domain.common.entities.Currency
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.gestor_dashboard.domain.entity.CardList
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.tools.utils.CurrencyFormatter
import com.repsol.tools.utils.UNLIMITED

object CardsMapper {

    fun toKpi(response: KpiResponse): KpiData {

        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        return KpiData(
            countCanceled = response.data.countCanceled,
            countInactive = response.data.countInactive,
            countActive = response.data.countActive,
            errorManager = error,
        )
    }

    fun toCardList(response: CardListResponse): CardList {
        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        val items: List<CardItem> = response.data.cardSearchListDto.map(::toCardItem)
        return CardList(
            items = items,
            errorManager = error
        )
    }

    private fun toCardItem(response: CardResponse): CardItem {

        return CardItem(
            creationDate = response.creationDate,
            descriptionState = response.descriptionState,
            descriptionCenterCost = response.descriptionCenterCost,
            featureDescription = response.featureDescription,
            descriptionControlType = response.descriptionControlType,
            cardNumber = response.cardNumber,
            driverName = response.driver,
            maxAmount = formatAmount(response.maxAmount),
            actAmount = formatAmount(response.actAmount),
            numberPlate = response.numberPlate,
            statusCode = response.statusCode,
        )
    }

    private fun formatAmount(amount: String): String = when {
        amount.contentEquals(UNLIMITED) -> UNLIMITED
        else -> CurrencyFormatter.formatCurrency(amount, Currency.PEN)
    }
}