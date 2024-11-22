package com.repsol.gestor_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse

interface CreditBalanceRemoteDS {

    suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalanceResponse>
}