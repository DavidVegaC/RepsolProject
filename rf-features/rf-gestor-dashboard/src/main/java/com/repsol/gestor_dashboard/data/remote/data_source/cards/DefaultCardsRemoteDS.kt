package com.repsol.gestor_dashboard.data.remote.data_source.cards

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.gestor_dashboard.data.remote.api.CardsApi
import com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse
import javax.inject.Inject

class DefaultCardsRemoteDS @Inject constructor(
    private val api: CardsApi
): CardsRemoteDs {

    override suspend fun getKpi(clientId: String): DataOutput<KpiResponse> {
        return safeApiCall { api.getKpi(clientId) }
    }
}