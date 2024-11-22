package com.repsol.gestor_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.gestor_dashboard.domain.entity.Download

interface IndexRepository {

    suspend fun postDownload(clientId: String): DataOutput<Download>

    suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalance>
}