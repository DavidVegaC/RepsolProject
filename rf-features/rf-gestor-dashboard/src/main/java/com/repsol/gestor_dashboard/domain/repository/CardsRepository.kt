package com.repsol.gestor_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.gestor_dashboard.domain.entity.CardList
import com.repsol.gestor_dashboard.domain.entity.KpiData

interface CardsRepository {

    suspend fun getKpi(clientId: String): DataOutput<KpiData>

    suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardList>
}