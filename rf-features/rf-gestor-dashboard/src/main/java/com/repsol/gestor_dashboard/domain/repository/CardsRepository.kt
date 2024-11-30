package com.repsol.gestor_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput

interface CardsRepository {

    suspend fun getKpi(clientId: String): DataOutput<com.repsol.gestor_dashboard.domain.entity.KpiData>
}