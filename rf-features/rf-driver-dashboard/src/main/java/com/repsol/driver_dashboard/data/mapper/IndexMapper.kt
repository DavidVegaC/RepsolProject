package com.repsol.driver_dashboard.data.mapper

import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.dto.response.CardResponse
import com.repsol.core_domain.common.entities.Currency
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.driver_dashboard.domain.entity.DriverData
import com.repsol.tools.utils.Formatters
import com.repsol.tools.utils.UNLIMITED

object IndexMapper {

    fun toCardList(response: CardListResponse): DriverData {

        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        val list: List<CardResponse> = response.data.cardSearchListDto
        val firstItem: CardResponse? = list.firstOrNull()

        return DriverData(
            driverName = firstItem?.driver.orEmpty(),
            creationDate = firstItem?.creationDate.orEmpty(),
            descriptionCenterCost = firstItem?.descriptionCenterCost.orEmpty(),
            featureDescription = firstItem?.featureDescription.orEmpty(),
            descriptionControlType = firstItem?.descriptionControlType.orEmpty(),
            cardNumber = firstItem?.cardNumber.orEmpty(),
            maxAmount = formatAmount(firstItem?.maxAmount.orEmpty()),
            actAmount = formatAmount(firstItem?.actAmount.orEmpty()),
            errorManager = error,
        )
    }

    private fun formatAmount(amount: String): String = when {
        amount.contentEquals(UNLIMITED) -> UNLIMITED
        else -> Formatters.formatCurrency(amount, Currency.PEN)
    }
}