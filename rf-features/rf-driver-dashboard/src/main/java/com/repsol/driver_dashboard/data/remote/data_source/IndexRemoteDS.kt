package com.repsol.driver_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.driver_dashboard.data.remote.dto.request.CardListRequest
import com.repsol.driver_dashboard.data.remote.dto.response.CardListResponse

interface IndexRemoteDS {

    suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardListResponse>

}