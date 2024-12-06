package com.repsol.core_data.common.remote.shared.api

import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.dto.request.VehicleListRequest
import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.dto.response.VehicleListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SharedApi {

    @POST("v2/Card/list")
    suspend fun postCardList(
        @Header("ClientId") clientId: String,
        @Body request: CardListRequest,
    ): Response<CardListResponse>

    @POST("v2/Vehicle")
    suspend fun postVehicleList(
        @Header("ClientId") clientId: String,
        @Body request: VehicleListRequest,
    ): Response<VehicleListResponse>
}