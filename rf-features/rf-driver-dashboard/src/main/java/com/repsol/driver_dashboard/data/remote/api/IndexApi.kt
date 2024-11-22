package com.repsol.driver_dashboard.data.remote.api

import com.repsol.driver_dashboard.data.remote.dto.request.CardListRequest
import com.repsol.driver_dashboard.data.remote.dto.response.CardListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IndexApi {

    @POST("v2/Card/list")
    suspend fun postCardList(
        @Header("ClientId") clientId: String,
        @Header("UserEmail") userEmail: String,
        @Body request: CardListRequest,
    ): Response<CardListResponse>
}