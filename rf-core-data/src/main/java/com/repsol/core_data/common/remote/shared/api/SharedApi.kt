package com.repsol.core_data.common.remote.shared.api

import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.dto.response.CardListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SharedApi {

    @POST("v2/Card/list")
    suspend fun postCardList(
        @Header("ClientId") clientId: String,
        @Header("UserEmail") userEmail: String,
        @Body request: CardListRequest,
    ): Response<CardListResponse>
}