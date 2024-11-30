package com.repsol.gestor_dashboard.data.remote.api

import com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CardsApi {

    @GET("v2/Card/kpi")
    suspend fun getKpi(
        @Header("ClientId") clientId: String,
    ): Response<KpiResponse>
}