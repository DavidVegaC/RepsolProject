package com.repsol.gestor_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.CreditBalance

interface CreditBalanceRepository {

    suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalance>
}