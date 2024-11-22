package com.repsol.gestor_dashboard.data.remote.api

import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Header

interface CreditBalanceApi {

    @GET("v2/Dashboard/creditBalance")
    suspend fun getCreditBalance(
        @Header("ClientId") clientId: String,
    ): Response<CreditBalanceResponse>
}