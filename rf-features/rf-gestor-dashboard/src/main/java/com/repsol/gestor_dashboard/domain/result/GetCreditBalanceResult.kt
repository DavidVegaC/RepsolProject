package com.repsol.gestor_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.railway.isSuccessful

sealed class GetCreditBalanceResult {
    data class Success(val data: CreditBalance) : GetCreditBalanceResult()
    data class Error(val message: String) : GetCreditBalanceResult()
}

fun DataOutput<CreditBalance>.handleResult(): GetCreditBalanceResult {
    if (this.isSuccessful()) {
        val creditBalance: CreditBalance = this.data
        val errorManager = creditBalance.errorManager
        if (errorManager.code.contentEquals("0").not()) {
            return GetCreditBalanceResult.Error(message = errorManager.message.orEmpty())
        }
        return if (hasError(creditBalance)) {
            GetCreditBalanceResult.Error(message = "Datos no disponible")
        } else {
            GetCreditBalanceResult.Success(data)
        }
    } else {
        val error: DataError.Default = this.error as DataError.Default
        return GetCreditBalanceResult.Error(message = error.message)
    }
}

private fun hasError(data: CreditBalance): Boolean {
    return data.debtAmount.contentEquals("Cargando..") ||
            data.amount.contentEquals("Cargando..")
}
