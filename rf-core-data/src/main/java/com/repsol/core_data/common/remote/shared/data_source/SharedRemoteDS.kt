package com.repsol.core_data.common.remote.shared.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.dto.request.VehicleListRequest
import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.dto.response.VehicleListResponse

interface SharedRemoteDS {

    suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardListResponse>

    suspend fun postVehicleList(
        clientId: String,
        request: VehicleListRequest,
    ): DataOutput<VehicleListResponse>
}