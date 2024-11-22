package com.repsol.driver_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.driver_dashboard.data.remote.api.IndexApi
import com.repsol.driver_dashboard.data.remote.dto.request.CardListRequest
import com.repsol.driver_dashboard.data.remote.dto.response.CardListResponse
import javax.inject.Inject

class DefaultIndexRemoteDS @Inject constructor(
    private val api: IndexApi
): IndexRemoteDS {

    override suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardListResponse> {
        return safeApiCall { api.postCardList(clientId, userEmail, request) }
    }
}