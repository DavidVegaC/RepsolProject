package com.repsol.core_data.common.remote.shared.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.dto.response.CardListResponse
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.core_data.common.remote.shared.api.SharedApi
import javax.inject.Inject

class DefaultSharedRemoteDS @Inject constructor(
    private val api: SharedApi
): SharedRemoteDS {

    override suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardListResponse> {
        return safeApiCall { api.postCardList(clientId, request) }
    }
}