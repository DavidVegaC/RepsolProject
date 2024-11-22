package com.repsol.gestor_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.gestor_dashboard.data.remote.api.CreditBalanceApi
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import javax.inject.Inject

class DefaultCreditBalanceRemoteDS @Inject constructor(
    private val api: CreditBalanceApi
): CreditBalanceRemoteDS {

    override suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalanceResponse> {
        return safeApiCall { api.getCreditBalance(clientId) }
    }
}