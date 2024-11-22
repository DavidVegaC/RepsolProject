package com.repsol.gestor_dashboard.data.mapper

import com.repsol.core_data.common.remote.dto.response.ErrorManagerResponse
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import com.repsol.gestor_dashboard.data.remote.dto.response.DataResponse
import com.repsol.gestor_dashboard.domain.entity.CreditBalance


object CreditBalanceMapper {

    fun toCreditBalance(response: CreditBalanceResponse): CreditBalance {

        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        return CreditBalance(
            balance = response.data.balance,
            lineCred = response.data.lineCred,
            paymentDeadLine = response.data.paymentDeadLine,
            excedido = response.data.excedido,
            fechaCaducidad = response.data.fechaCaducidad,
            recargar = response.data.recargar,
            importe = response.data.importe,
            deuda = response.data.deuda,
            errorManager = error
        )

    }
}
