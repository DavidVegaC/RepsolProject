package com.repsol.gestor_dashboard.data.remote.data_source.cards

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse

interface CardsRemoteDs {

    suspend fun getKpi(
        clientId: String,
    ): DataOutput<KpiResponse>
}