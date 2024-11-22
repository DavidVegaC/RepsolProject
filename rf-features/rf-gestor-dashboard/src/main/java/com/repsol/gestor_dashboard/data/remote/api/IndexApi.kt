package com.repsol.gestor_dashboard.data.remote.api

import com.repsol.gestor_dashboard.data.remote.dto.request.DownloadRequest
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import com.repsol.gestor_dashboard.data.remote.dto.response.DownloadResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IndexApi {

    @POST("v2/CurrentPrice/Download")
    suspend fun postDownload(
        @Header("ClientId") clientId: String,
        @Body request: DownloadRequest,
    ): Response<DownloadResponse>

    @GET("v2/Dashboard/creditBalance")
    suspend fun getCreditBalance(
        @Header("ClientId") clientId: String,
    ): Response<CreditBalanceResponse>
}