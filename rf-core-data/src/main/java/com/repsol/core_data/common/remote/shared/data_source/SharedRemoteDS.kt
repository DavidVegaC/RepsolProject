package com.repsol.core_data.common.remote.shared.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.dto.response.CardListResponse

interface SharedRemoteDS {

    suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardListResponse>
}