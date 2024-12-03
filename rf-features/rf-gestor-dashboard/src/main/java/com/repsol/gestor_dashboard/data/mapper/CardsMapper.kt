package com.repsol.gestor_dashboard.data.mapper

import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.dto.response.CardResponse
import com.repsol.core_data.common.remote.dto.response.PaginationResponse
import com.repsol.core_domain.common.entities.Currency
import com.repsol.core_domain.common.entities.PaginationState
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.gestor_dashboard.domain.entity.CardList
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.tools.utils.Formatters
import com.repsol.tools.utils.NO
import com.repsol.tools.utils.UNLIMITED
import com.repsol.tools.utils.YES

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
        val pagination: PaginationState = toPaginationState(response.data.pagination)
        return CardList(
            items = items,
            pagination = pagination,
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
            codeFeaturesCard = response.codeFeaturesCard,
            typeDocumentDescription = response.typeDocumentDescription,
            numberDocument = response.numberDocument,
            descriptionBusinessRule = response.descriptionBusinessRule,
            descriptionStateRequestPhysicalCard = response.descriptionStateRequestPhysicalCard,
            hasControlKm = if (response.km) YES else NO,
            descriptionResetAmount = response.descriptionResetAmount,
        )
    }

    private fun formatAmount(amount: String): String = when {
        amount.contentEquals(UNLIMITED) -> UNLIMITED
        else -> Formatters.formatCurrency(amount, Currency.PEN)
    }

    private fun toPaginationState(paginationState: PaginationResponse): PaginationState {

        return PaginationState(
            currentPage = paginationState.currentPage,
            totalRows = paginationState.totalRows,
            pageSize = paginationState.pageSize,
            totalPage = paginationState.totalPage,
        )
    }
}